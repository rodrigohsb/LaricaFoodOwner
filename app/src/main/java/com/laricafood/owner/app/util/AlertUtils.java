package com.laricafood.owner.app.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by Rodrigo on 22/06/15.
 */
public class AlertUtils
{
    private final Context context;

    public AlertUtils (Context context)
    {
        this.context = context;
    }

    public AlertDialog getAlertDialog (String title, String message, boolean cancelable, DialogInterface.OnClickListener positiveButton, String positiveButtonTitle, DialogInterface.OnClickListener negativeButton, String negativeButtonTitle)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (title != null)
        {
            builder.setTitle(title);
        }
        if (message != null)
        {
            builder.setMessage(message);
        }
        if (positiveButton != null)
        {
            builder.setPositiveButton(positiveButtonTitle, positiveButton);
        }
        if (negativeButton != null)
        {
            builder.setNegativeButton(negativeButtonTitle, negativeButton);
        }
        builder.setCancelable(cancelable);

        return builder.create();
    }

    public AlertDialog getAlertDialog (View view, String title, String message, boolean cancelable, DialogInterface.OnClickListener positiveButton, String positiveButtonTitle, DialogInterface.OnClickListener negativeButton, String negativeButtonTitle)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (view != null)
        {
            builder.setView(view);
        }

        if (title != null)
        {
            builder.setTitle(title);
        }
        if (message != null)
        {
            builder.setMessage(message);
        }
        if (positiveButton != null)
        {
            builder.setPositiveButton(positiveButtonTitle, positiveButton);
        }
        if (negativeButton != null)
        {
            builder.setNegativeButton(negativeButtonTitle, negativeButton);
        }
        builder.setCancelable(cancelable);

        return builder.create();
    }

}
