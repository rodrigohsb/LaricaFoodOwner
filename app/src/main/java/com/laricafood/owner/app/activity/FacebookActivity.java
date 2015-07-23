package com.laricafood.owner.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.service.RegistrationService;
import com.laricafood.owner.app.util.Constants;

import java.util.Arrays;

/**
 * Created by Rodrigo on 23/06/15.
 */
public class FacebookActivity extends AppCompatActivity
{

    private Context ctx;
    private Boolean cameFromTutorial = false;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        ctx = this;
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_fb);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            cameFromTutorial = bundle.getBoolean(Constants.CAME_FROM_SPLASH_SCREEN_OR_TUTORIAL, false);
        }

        LoginButton loginButton = (LoginButton) findViewById(R.id.loginButton);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess (LoginResult loginResult)
            {
                startService(new Intent(ctx, RegistrationService.class));
                
                if (cameFromTutorial)
                {
                    startActivity(new Intent(ctx, NewAccountActivity.class));
                }
                finish();
            }

            @Override
            public void onCancel ()
            {
                finish();
            }

            @Override
            public void onError (FacebookException e)
            {
                ((Activity) ctx).runOnUiThread(new Runnable()
                {
                    @Override
                    public void run ()
                    {
                        Toast.makeText(ctx.getApplicationContext(), "Erro na Autenticacao. Não faz nada!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
    public void onBackPressed ()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        sharedPreferences.edit().putBoolean(Constants.ALREADY_SAW_TUTORIAL_BUT_NOT_LOGGED_ID, true).apply();
        finish();
    }
}
