package com.laricafood.owner.app.service;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Rodrigo on 04/07/15.
 */
public class InstanceIDListener extends InstanceIDListenerService
{
    @Override
    public void onTokenRefresh ()
    {
        startService(new Intent(this, RegistrationService.class));
    }
}
