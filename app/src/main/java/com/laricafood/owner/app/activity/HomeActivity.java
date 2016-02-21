package com.laricafood.owner.app.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.gson.Gson;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.async.AccountAsyncTask;
import com.laricafood.owner.app.async.FacebookImageAsyncTask;
import com.laricafood.owner.app.bean.Message;
import com.laricafood.owner.app.bean.User;
import com.laricafood.owner.app.persistence.MessageRepository;
import com.laricafood.owner.app.persistence.UserRepository;
import com.laricafood.owner.app.util.ConnectionUtils;
import com.laricafood.owner.app.util.Constants;

import java.util.List;


public class HomeActivity extends AppCompatActivity
{

    private Context ctx;

    private static final int NEW_LARICA_REQUEST_CODE = 100;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ctx = this;
        FacebookSdk.sdkInitialize(ctx);

        setContentView(R.layout.activity_account);

        ListView list = (ListView) findViewById(R.id.accountList);
        TextView accountState = (TextView) findViewById(R.id.accountState);
        ImageView profilePicture = (ImageView) findViewById(R.id.profilePicture);
        ImageView accountErrorImage = (ImageView) findViewById(R.id.accountErrorImage);
        LinearLayout accountMessages = (LinearLayout) findViewById(R.id.accountMessages);
        TextView qntdEstabelecimentos = (TextView) findViewById(R.id.qntdEstabelecimentos);
        TextView accountCountUnreadMessages = (TextView) findViewById(R.id.accountCountUnreadMessages);
        TextView accountNoEstabelecimentoTitle = (TextView) findViewById(R.id.accountNoEstabelecimentoTitle);
        TextView accountNoEstabelecimentoSubTitle = (TextView) findViewById(R.id.accountNoEstabelecimentoSubTitle);


        if (ConnectionUtils.isOnline(ctx))
        {
            new FacebookImageAsyncTask(profilePicture).execute();
        }
        new AccountAsyncTask(ctx, accountState, accountErrorImage, accountNoEstabelecimentoTitle, accountNoEstabelecimentoSubTitle, qntdEstabelecimentos, list).execute();


        int countUnreadMessages = countUnreadMessages();

        if (countUnreadMessages > 0)
        {
            accountCountUnreadMessages.setTypeface(null, Typeface.BOLD);
        }
        accountCountUnreadMessages.setText(String.valueOf(countUnreadMessages));

        accountMessages.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent it = new Intent(ctx, MessagesActivity.class);
                startActivity(it);
            }
        });
    }


    private int countUnreadMessages ()
    {
//        Teste.createMessages(ctx);
        final List<Message> messages = MessageRepository.getInstance(ctx).getAll();

        if (messages.isEmpty())
        {
            return 0;
        }

        int countUnreadMessages = 0;

        for (Message m : messages)
        {
            if (m.isUnread())
            {
                countUnreadMessages++;
            }
        }
        return countUnreadMessages;

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_add:
                startActivityForResult(new Intent(ctx, NewEstabelecimentoActivity.class), NEW_LARICA_REQUEST_CODE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed ()
    {
        finish();
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
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if (requestCode == NEW_LARICA_REQUEST_CODE && resultCode == RESULT_FIRST_USER)
        {
            //TODO Atualizar a lista com a larica criada
        }
    }

    User getUser (Context ctx)
    {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        String json = sharedPreferences.getString(Constants.USER, Constants.USER);
        if (json != null)
        {
            return new Gson().fromJson(json, User.class);
        }
        User user = UserRepository.getInstance(ctx).getUser();
        if (user != null)
        {
            return user;
        }
        return null;
    }

}
