package com.laricafood.owner.app.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.laricafood.owner.app.bean.Larica;

/**
 * Created by Rodrigo on 26/06/15.
 */
public class LaricaChangeStatusAsyncTask extends AsyncTask<Void, Void, Boolean>
{
    private ProgressDialog progressDialog;
    private DialogInterface dialog;
    private Larica larica;
    private BaseAdapter adapter;
    private Context ctx;

    public LaricaChangeStatusAsyncTask (Context ctx, Larica larica, DialogInterface dialog, BaseAdapter adapter)
    {
        this.ctx = ctx;
        this.larica = larica;
        this.dialog = dialog;
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute ()
    {
        super.onPreExecute();
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setCancelable(false);

        if (larica.isOpen())
        {
            progressDialog.setMessage("Fechando larica . . .");
        }
        else
        {
            progressDialog.setMessage("Abrindo larica . . .");
        }
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground (Void... objs)
    {

        //TODO Deletar larica no servidor
        boolean done = true;

        for (int i = 0 ; i < 1000000000 ; i++)
        {

        }
        return done;
    }

    @Override
    protected void onPostExecute (Boolean done)
    {
        super.onPostExecute(done);

        final String message;

        if (done)
        {
            larica.setOpen(!larica.isOpen());
            if (larica.isOpen())
            {
                message = "Larica aberta. Boas vendas!";
            }
            else
            {
                message = "Larica fechada. Bom descanso!";
            }
            adapter.notifyDataSetChanged();
        }
        else
        {
            if (larica.isOpen())
            {
                message = "Não foi possível registrar o fechamento da larica.";
            }
            else
            {
                message = "Não foi possível registrar a abertura da larica.";
            }
        }

        progressDialog.dismiss();

        dialog.dismiss();

        ((Activity) ctx).runOnUiThread(new Runnable()
        {
            @Override
            public void run ()
            {
                Toast.makeText(ctx.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}