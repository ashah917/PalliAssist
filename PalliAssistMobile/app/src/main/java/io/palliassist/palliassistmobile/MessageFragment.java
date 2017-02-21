package io.palliassist.palliassistmobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.twilio.ipmessaging.Channel;
import com.twilio.ipmessaging.ChannelListener;
import com.twilio.ipmessaging.Channels;
import com.twilio.ipmessaging.Constants;
import com.twilio.ipmessaging.IPMessagingClientListener;
import com.twilio.ipmessaging.Member;
import com.twilio.ipmessaging.Message;
import com.twilio.ipmessaging.Messages;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.palliassist.palliassistmobile.twilio.application.TwilioApplication;
import io.palliassist.palliassistmobile.twilio.ui.MessageViewHolder;
import io.palliassist.palliassistmobile.twilio.util.BasicIPMessagingClient;
import io.palliassist.palliassistmobile.twilio.util.HttpHelper;
import io.palliassist.palliassistmobile.twilio.util.ILoginListener;
import io.palliassist.palliassistmobile.twilio.util.Logger;
import uk.co.ribot.easyadapter.EasyAdapter;

import static io.palliassist.palliassistmobile.LoginActivity.USER;


/**
 * Created by Steven on 1/28/2017.
 */

public class MessageFragment extends Fragment implements ChannelListener, ILoginListener, IPMessagingClientListener {

    private String user;
    // Authentication
    private static final String AUTH_SCRIPT = "https://palliassist-dev-us.azurewebsites.net/token";
    //private static final String AUTH_SCRIPT = "http://2ddd42ce.ngrok.io/token";
    private String capabilityToken = null;
    private BasicIPMessagingClient basicClient;
    private ProgressDialog progressDialog;
    public static String token = null;
    // Chat
    private static final String TAG = "ChatActivityTag";
    private List<Message> messages = new ArrayList<>();
    private EasyAdapter<Message> adapter;
    public static String channelName = "Demo Channel";

    private ListView lvChat;
    private EditText etMessage;

    private Channel channel;

    private String currentChat;

    public static String local_author;

    private View layout = null;

    //handler


    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void runonUI(Runnable runnable) {
        mHandler.post(runnable);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_chat, container, false);
        layout = rootView;
        basicClient = TwilioApplication.get().getBasicClient();
        Log.d(TAG, "basicClient: " + basicClient);
        System.out.println("basicClient: " + basicClient);
        if (basicClient != null) {
            // Authentication
                authenticateUser();

      }

        // Chat
        currentChat = MessageFragment.local_author;

        // Message Text
        this.etMessage = (EditText) rootView.findViewById(R.id.etMessage);

        // Send Button
        Button btSend = (Button) rootView.findViewById(R.id.btSend);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etMessage.getText().toString();
                if (channel != null) {
                    Messages messagesObject = channel.getMessages();
                    final Message message = messagesObject.createMessage(input);
                    messagesObject.sendMessage(message, new Constants.StatusListener() {
                        @Override
                        public void onSuccess() {
                            Log.e(TAG, "Successful at sending message.");
                            runonUI(new Runnable() {
                                @Override
                                public void run() {
                                    messages.add(message);
                                    adapter.notifyDataSetChanged();
                                    etMessage.setText("");
                                    etMessage.requestFocus();
                                }
                            });
                        }

                        @Override
                        public void onError() {
                            Log.e(TAG, "Error sending message.");
                        }
                    });
                }
            }
        });
        return rootView;
    }


    private class CustomMessageComparator implements Comparator<Message> {
        @Override
        public int compare(Message lhs, Message rhs) {
            if (lhs == null) {
                return (rhs == null) ? 0 : -1;
            }
            if (rhs == null) {
                return 1;
            }
            return lhs.getTimeStamp().compareTo(rhs.getTimeStamp());
        }
    }

    private void setupListView() {

        Messages messagesObject = channel.getMessages();

        if (messagesObject != null) {
            Message[] messagesArray = messagesObject.getMessages();
            if (messagesArray.length > 0) {
                messages = new ArrayList<>(Arrays.asList(messagesArray));
                Collections.sort(messages, new CustomMessageComparator());
            }
        }

        adapter = new EasyAdapter<>(this.getActivity(), MessageViewHolder.class, messages,
                new MessageViewHolder.OnMessageClickListener() {

                    @Override
                    public void onMessageClicked(Message message) {
                        // TODO: Implement options for deletion or edit
                    }
                });

        // List View
        lvChat = (ListView) layout.findViewById(R.id.lvChat);
        lvChat.setAdapter(adapter);

        if (lvChat != null) {
            lvChat.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            lvChat.setStackFromBottom(true);
            adapter.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    lvChat.setSelection(adapter.getCount() - 1);
                }
            });
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onMessageAdd(Message message) {
        setupListView();
        if (message != null) {
            Log.d(TAG, "Received onMessageAdd event");
        }
    }

    @Override
    public void onMessageChange(Message message) {
        if (message != null) {
            Log.d(TAG, "Received onMessageChange event");
        }
    }

    @Override
    public void onMessageDelete(Message message) {

    }

    @Override
    public void onMemberJoin(Member member) {
        if (member != null) {
            Log.d(TAG, member.getIdentity() + " joined");
        }
    }

    @Override
    public void onMemberChange(Member member) {

    }

    @Override
    public void onMemberDelete(Member member) {

    }

    @Override
    public void onAttributesChange(Map<String, String> map) {

    }

    @Override
    public void onTypingStarted(Member member) {

    }

    @Override
    public void onTypingEnded(Member member) {

    }

    @Override
    public void onChannelAdd(Channel channel) {
        Log.d(TAG, "Received onChannelAdd callback " + channel.getUniqueName());
    }

    @Override
    public void onChannelChange(Channel channel) {
        Log.d(TAG, "Received onChannelChange callback " + channel.getUniqueName());
    }

    @Override
    public void onChannelDelete(Channel channel) {

    }

    @Override
    public void onError(int errorCode, String errorText) {
        Log.d(TAG, "Received onError callback " + errorCode + " " + errorText);
    }

    @Override
    public void onAttributesChange(String s) {
        Log.d(TAG, "Received onAttributesChange callback");
    }

    @Override
    public void onChannelHistoryLoaded(Channel channel) {
        Log.d(TAG, "Received onChannelHistoryLoaded callback " + channel.getUniqueName());
    }

    @Override
    public void onLoginStarted() {
        Log.d(TAG, "Log in started");
    }

    @Override
    public void onLoginFinished() {
        MessageFragment.this.progressDialog.dismiss();
        basicClient.getIpMessagingClient().setListener(MessageFragment.this);

        Channels channelsLocal = basicClient.getIpMessagingClient().getChannels();
        // Creates a new public channel if one doesn't already exist
        if (channelsLocal.getChannelByUniqueName(channelName) != null) {
            //join it
            channel = channelsLocal.getChannelByUniqueName(channelName);
            channel.setListener(MessageFragment.this);

            // Listen for channel join status
            Constants.StatusListener joinListener = new Constants.StatusListener() {
                @Override
                public void onSuccess() {
                    runonUI(new Runnable() {
                        @Override
                        public void run() {
                            setupListView();
                        }
                    });
                    Log.d(TAG, "Successfully joined existing channel");
                }

                @Override
                public void onError() {
                    Log.e(TAG, "failed to join existing channel");
                }
            };

            // join the channel
            channel.join(joinListener);
        } else {

            final Map<String, String> attributes = new HashMap<>();
            attributes.put("topic", MessageFragment.local_author + "Chat");

            Map<String, Object> options = new HashMap<>();
            //Change
            options.put(Constants.CHANNEL_FRIENDLY_NAME, channelName);
            options.put(Constants.CHANNEL_UNIQUE_NAME, channelName);
            options.put(Constants.CHANNEL_TYPE, Channel.ChannelType.CHANNEL_TYPE_PUBLIC);
            options.put("attributes", attributes);

            channelsLocal.createChannel(options, new Constants.CreateChannelListener() {
                @Override
                public void onCreated(final Channel newChannel) {
                    Log.e(TAG, "Successfully created a channel with options");
                    channel = newChannel;
                    runonUI(new Runnable() {
                        @Override
                        public void run() {
                            setupListView();
                        }
                    });
                }


                @Override
                public void onError() {
                    Log.e(TAG, "failed to create new channel");
                }
            });
        }
    }

    @Override
    public void onLoginError(String errorMessage) {
        MessageFragment.this.progressDialog.dismiss();
        Log.d(TAG, "Error logging in : " + errorMessage);
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLogoutFinished() {

    }

    private void authenticateUser() {
        try {
            token = AUTH_SCRIPT + "?identity=" +USER;
            Log.d(TAG,"TOKEN PASSING: " + token);
            //new GetCapabilityTokenAsyncTask().execute(AUTH_SCRIPT).get();
           new GetCapabilityTokenAsyncTask().execute(token).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class GetCapabilityTokenAsyncTask extends AsyncTask<String, Void, String> {
        private String urlString;

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    MessageFragment.this.basicClient.doLogin(MessageFragment.this, urlString);
                }
            }).start();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MessageFragment.this.progressDialog = ProgressDialog.show(MessageFragment.this.getActivity(), "",
                    "Connecting. Please wait...", true);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                urlString = params[0];
                System.out.println("params0: " + params[0]);
                capabilityToken = new String(HttpHelper.httpGet(urlString),"UTF-8");
                System.out.println("capabilityToken" + capabilityToken);
                JSONObject responseObject = new JSONObject(capabilityToken);
                String token = responseObject.getString("token");
                MessageFragment.local_author = responseObject.getString("identity");

                basicClient.setCapabilityToken(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return capabilityToken;
        }
    }


}
