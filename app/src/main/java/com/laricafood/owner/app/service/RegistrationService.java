package com.laricafood.owner.app.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.laricafood.owner.app.util.ConnectionUtils;
import com.laricafood.owner.app.util.Constants;

import java.io.IOException;

/**
 * Created by Rodrigo on 04/07/15.
 */
public class RegistrationService extends IntentService
{

    private static final String TAG = "RegistrationService";

    public RegistrationService ()
    {
        super(TAG);
    }

    @Override
    protected void onHandleIntent (Intent intent)
    {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        
        /* Checa se device esta conectado*/
        boolean isConnected = ConnectionUtils.isOnline(this);

        /* Token de acesso ao facebook*/
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        /* Perfil do usuario no facebook*/
        Profile profile = Profile.getCurrentProfile();
        
        /* Se o device ainda não está cadastrado no servidor, se está conectado a internet e se o usuario já está logado. */
        if ((!sharedPreferences.getBoolean(Constants.SENT_TOKEN_TO_SERVER, false)) && isConnected && accessToken != null && profile != null)
        {

            try
            {
                InstanceID instanceId = InstanceID.getInstance(this);

                String token = instanceId.getToken(Constants.SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                if (sentToServer(token))
                {
                    sharedPreferences.edit().putBoolean(Constants.SENT_TOKEN_TO_SERVER, true).apply();
                }

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private boolean sentToServer (String token)
    {

        Profile profile = Profile.getCurrentProfile();

        //TODO ENVIAR token para o servidor passando o profile
        //Server precisara inserir ou atualizar
        return true;
    }
}
