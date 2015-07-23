package com.laricafood.owner.app.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.facebook.Profile;
import com.google.gson.Gson;
import com.laricafood.owner.app.bean.User;
import com.laricafood.owner.app.persistence.UserRepository;
import com.laricafood.owner.app.util.Constants;

/**
 * Created by Rodrigo on 09/07/15.
 */
public class UserService extends IntentService
{

    private static final String TAG = "UserService";

    private Profile profile;
    SharedPreferences sharedPreferences;

    public UserService ()
    {
        super(TAG);
    }

    @Override
    protected void onHandleIntent (Intent intent)
    {

        profile = Profile.getCurrentProfile();

        if (profile != null)
        {

            User user = new User();
            user.setFacebookId(profile.getId());
            user.setFirstName(profile.getFirstName());
            user.setMiddleName(profile.getMiddleName());
            user.setLastName(profile.getLastName());
            user.setPictureUrl(profile.getProfilePictureUri(130, 130).toString());

            putInDataBase(user, this);
            putInCache(user);
        }
    }
    private void putInDataBase (User user, Context ctx)
    {
        try
        {
            UserRepository.getInstance(ctx).save(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void putInCache (User user)
    {

        try
        {
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(user);
            prefsEditor.putString(Constants.USER, json).apply();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
