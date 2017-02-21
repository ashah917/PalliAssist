package io.palliassist.palliassistmobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.twilio.ipmessaging.Channel;
import com.twilio.ipmessaging.Channels;

import java.util.ArrayList;
import java.util.List;

import io.palliassist.palliassistmobile.twilio.util.BasicIPMessagingClient;
import io.palliassist.palliassistmobile.twilio.util.Logger;
import uk.co.ribot.easyadapter.EasyAdapter;

/**
 * Created by Steven on 1/29/2017.
 */

public class ChannelFragment extends Fragment {

    private static final String[] CHANNEL_OPTIONS = { "Join" };
    private static final Logger logger = Logger.getLogger(ChannelFragment.class);
    private static final int JOIN = 0;

    private ListView listView;
    private BasicIPMessagingClient basicClient;
    private List<Channel> channels = new ArrayList<Channel>();
    private EasyAdapter<Channel> adapter;
    private AlertDialog createChannelDialog;
    private Channels channelsObject;
    private Channel[] channelArray;

    private static final Handler handler = new Handler();
    private AlertDialog incoingChannelInvite;
    private ProgressDialog progressDialog;

    private static Handler cHandler = new Handler(Looper.getMainLooper());

    public static void runonUI(Runnable runnable) {
        cHandler.post(runnable);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_channel, container, false);

        return rootView;
    }

}
