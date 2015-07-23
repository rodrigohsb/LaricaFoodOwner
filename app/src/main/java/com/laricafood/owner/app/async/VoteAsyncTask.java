package com.laricafood.owner.app.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Rodrigo on 26/06/15.
 */
public class VoteAsyncTask extends AsyncTask<Void, Void, Boolean>
{

    private ProgressDialog dialog;
    private Context ctx;

    public VoteAsyncTask (Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute ()
    {
        super.onPreExecute();
        dialog = new ProgressDialog(ctx);
        dialog.setMessage("Efetuando voto. Por favor, aguarde um instante...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected Boolean doInBackground (Void... params)
    {

        //TODO Salvar a nota (facebookId c/ o estabelecimentoId).

        boolean done = true;
        for (int i = 0 ; i < 1000000000 ; i++)
        {

        }
        return done;
    }

    @Override
    protected void onPostExecute (Boolean done)
    {
        dialog.dismiss();

        final String msg;
        if (done)
        {
            msg = "Voto computado! Obrigado!";
        }
        else
        {
            msg = "Não foi possível registrar seu voto no momento. Por favor, tente mais tarde.";
        }
        ((Activity) ctx).runOnUiThread(new Runnable()
        {
            @Override
            public void run ()
            {
                Toast.makeText(ctx.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        super.onPostExecute(done);
    }
}
