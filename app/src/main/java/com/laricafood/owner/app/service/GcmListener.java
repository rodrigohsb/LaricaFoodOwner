package com.laricafood.owner.app.service;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Rodrigo on 04/07/15.
 */
public class GcmListener extends GcmListenerService
{
    @Override
    public void onMessageReceived (String from, Bundle data)
    {
        super.onMessageReceived(from, data);

        //Criar Notificacao - NotificationUtils

    }
}
