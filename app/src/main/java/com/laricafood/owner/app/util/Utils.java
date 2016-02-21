package com.laricafood.owner.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.laricafood.owner.app.bean.User;
import com.laricafood.owner.app.persistence.UserRepository;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Rodrigo on 01/03/15.
 */
public class Utils
{
    public static User getUser (Context ctx)
    {

        User user = getUserPreferences(ctx);

        if (user != null)
        {
            return user;
        }

        user = getUserDB(ctx);

        if (user != null)
        {
            return user;
        }

        //TODO Buscar no server!!            

        return null;
    }

    private static User getUserPreferences (Context ctx)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        // Busca no cache
        String json = sharedPreferences.getString(Constants.USER, Constants.USER);

        if (json != null)
        {
            try
            {
                return new Gson().fromJson(json, User.class);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static User getUserDB (Context ctx)
    {
        try
        {
            User user = UserRepository.getInstance(ctx).getUser();
            //TODO Colocar no cache
            return user;
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}