package com.laricafood.owner.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.facebook.appevents.AppEventsLogger;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.bean.Message;
import com.laricafood.owner.app.persistence.MessageRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Rodrigo on 23/06/15.
 */
public class MessageDetailsActivity extends AppCompatActivity
{

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm");

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        Context ctx = this;

        TextView messageDetailsTitle = (TextView) findViewById(R.id.messageDetailsTitle);

        TextView messageDetailsDate = (TextView) findViewById(R.id.messageDetailsDate);

//        TextView messageDetailsContent = (TextView) findViewById(R.id.messageDetailsContent);

        DocumentView messageDetailsContent = (DocumentView) findViewById(R.id.messageDetailsContent);

        Bundle bundle = getIntent().getExtras();
        Message message = (Message) bundle.getSerializable("message");

        if (message.isUnread())
        {
            message.setUnread(false);
            MessageRepository.getInstance(ctx).update(message);
        }

        messageDetailsTitle.setText(message.getTitle());

        if (message.getDate() != null)
        {
            messageDetailsDate.setText(format.format(message.getDate()));
        }
        else
        {
            messageDetailsDate.setVisibility(View.GONE);
        }

        messageDetailsContent.setText(message.getContent());
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause ()
    {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onBackPressed ()
    {
        finish();
    }
}
