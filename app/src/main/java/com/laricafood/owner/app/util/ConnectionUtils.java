package com.laricafood.owner.app.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;

/**
 * Created by Rodrigo on 24/06/15.
 */
public class ConnectionUtils
{
    public static boolean isOnline (Context ctx)
    {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable();
    }

    public static boolean isGpsEnable (Context ctx)
    {
        LocationManager service = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        return service.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
