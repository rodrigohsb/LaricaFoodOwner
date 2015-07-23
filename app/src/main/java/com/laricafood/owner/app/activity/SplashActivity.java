package com.laricafood.owner.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.bean.User;
import com.laricafood.owner.app.service.RegistrationService;
import com.laricafood.owner.app.util.Constants;
import com.laricafood.owner.app.util.Utils;

/**
 * Created by Rodrigo on 19/06/15.
 */
public class SplashActivity extends AppCompatActivity
{

    private Context ctx;

    private SharedPreferences sharedPreferences;

    private User user;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        ctx = this;
        FacebookSdk.sdkInitialize(this);

        setContentView(R.layout.activity_splash);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        user = Utils.getUser(ctx);

        /** Primeiro acesso ou limpou o cache */
        Boolean isFirtTimeOrCleanCache = sharedPreferences.getBoolean(Constants.IS_FIRST_TIME, true);

        /** Ja viu o tutorial mas ainda nao logou */
        Boolean alreadySawTutorial = sharedPreferences.getBoolean(Constants.ALREADY_SAW_TUTORIAL_BUT_NOT_LOGGED_ID, false);

        final Intent it;

        if (isFirtTimeOrCleanCache)
        {
            it = new Intent(ctx, TutorialActivity.class);
        }
        else if (alreadySawTutorial && user == null)
        {
            it = new Intent(ctx, FacebookActivity.class);
        }
        else
        {
            startService(new Intent(this, RegistrationService.class));

            Boolean hasNoAccount = sharedPreferences.getBoolean(Constants.LOGGED_IN_BUT_HAS_NO_ACCOUNT, true);

            if (hasNoAccount)
            {
                it = new Intent(ctx, PaymentActivity.class);
            }
            else
            {
                it = new Intent(ctx, HomeActivity.class);
            }

        }

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run ()
            {
                startActivity(it);
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
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
}
