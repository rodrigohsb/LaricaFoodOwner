package com.laricafood.owner.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.util.Constants;

/**
 * Created by Rodrigo on 04/07/15.
 */
public class TutorialActivity extends AppCompatActivity
{

    private Context ctx;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ctx = this;
        FacebookSdk.sdkInitialize(this);

        setContentView(R.layout.activity_tutorial);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        findViewById(R.id.tutorialButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent it;
                if (Profile.getCurrentProfile() == null)
                {
                    sharedPreferences.edit().putBoolean(Constants.IS_FIRST_TIME, false).apply();
                    it = new Intent(ctx, FacebookActivity.class);
                }
                else
                {
                    it = new Intent(ctx, HomeActivity.class);
                }
                startActivity(it);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed ()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        sharedPreferences.edit().putBoolean(Constants.ALREADY_SAW_TUTORIAL_BUT_NOT_LOGGED_ID, true).apply();
        finish();
    }
}
