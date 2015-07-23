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
    public static void CopyStream (InputStream is, OutputStream os)
    {
        final int buffer_size = 1024;
        try
        {
            byte[] bytes = new byte[buffer_size];
            for (; ; )
            {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex)
        {
        }
    }

    public static User getUser (Context ctx)
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

        //Busca no banco
        try
        {
            User user = UserRepository.getInstance(ctx).getUser();
            //TODO Colocar no cache
            return user;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //Não tem no cache e nem no banco.
            //TODO Buscar no server!!
        }
        return null;
    }
}