package io.palliassist.palliassistmobile.twilio.ui;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.palliassist.palliassistmobile.R;
import io.palliassist.palliassistmobile.twilio.ui.ChatActivity;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

import com.twilio.ipmessaging.Message;

/**
 * Created by User on 11/27/2016.
 */



@LayoutId(R.layout.message_item_layout)
public class MessageViewHolder extends ItemViewHolder<Message> {

    @ViewId(R.id.body)
    TextView body;

    @ViewId(R.id.txtInfo)
    TextView txtInfo;

    @ViewId(R.id.singleMessageContainer)
    LinearLayout singleMessageContainer;

    View view;

    public MessageViewHolder(View view) {
        super(view);
        this.view = view;
    }

    @Override
    public void onSetListeners() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnMessageClickListener listener = getListener(OnMessageClickListener.class);
                if (listener != null) {
                    listener.onMessageClicked(getItem());
                }
            }
        });
    }

    @Override
    public void onSetValues(Message message, PositionInfo pos) {
        StringBuilder textInfo = new StringBuilder();
        if(message != null) {
            String dateString = message.getTimeStamp();
            if(dateString != null) {
                textInfo.append(message.getAuthor()).append(":").append(dateString);
            } else {
                textInfo.append(message.getAuthor());
            }
            txtInfo.setText(textInfo.toString());
            body.setText(message.getMessageBody());

            // Check for current author
            if(message.getAuthor().compareTo(ChatActivity.local_author) == 0 || message.getAuthor().equals("")){
                body.setBackgroundResource(R.drawable.bubble_a);
                singleMessageContainer.setGravity(Gravity.END);
            }else{
                body.setBackgroundResource(R.drawable.bubble_b);
                singleMessageContainer.setGravity(Gravity.START);
            }
        }

    }

    public interface OnMessageClickListener {
        void onMessageClicked(Message message);
    }
}
