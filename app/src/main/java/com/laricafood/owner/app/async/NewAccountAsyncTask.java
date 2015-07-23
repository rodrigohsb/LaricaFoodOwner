package com.laricafood.owner.app.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.laricafood.owner.app.activity.HomeActivity;
import com.laricafood.owner.app.service.UserService;
import com.laricafood.owner.app.util.Constants;

/**
 * Created by Rodrigo on 08/07/15.
 */
public class NewAccountAsyncTask extends AsyncTask<Void, Void, Boolean>
{

    private Context ctx;
    private ProgressDialog dialog;

    private SharedPreferences sharedPreferences;

    public NewAccountAsyncTask (Context ctx)
    {
        this.ctx = ctx;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    @Override
    protected void onPreExecute ()
    {
        super.onPreExecute();
        dialog = new ProgressDialog(ctx);
        dialog.setMessage("Por favor aguarde,\nCriando conta . . .");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected Boolean doInBackground (Void... params)
    {

        boolean done = true;

        //TODO Enviar o facebookId com o tipo e as informações do pagamento para o server

        try
        {
            //Salva no DB e em cache
            ctx.startService(new Intent(ctx, UserService.class));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        for (int i = 0 ; i < 1000000000 ; i++)
        {

        }

        return done;
    }

    @Override
    protected void onPostExecute (Boolean done)
    {
        dialog.dismiss();

        if (done)
        {
            ((Activity) ctx).runOnUiThread(new Runnable()
            {
                @Override
                public void run ()
                {
                    Toast.makeText(ctx, "Conta criada!", Toast.LENGTH_SHORT).show();
                }
            });
            sharedPreferences.edit().putBoolean(Constants.LOGGED_IN_BUT_HAS_NO_ACCOUNT, false).apply();
            ctx.startActivity(new Intent(ctx, HomeActivity.class));
            ((Activity) ctx).finish();
        }
        else
        {
            ((Activity) ctx).runOnUiThread(new Runnable()
            {
                @Override
                public void run ()
                {
                    Toast.makeText(ctx, "Não foi possível criar conta.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        super.onPostExecute(done);
    }

}
