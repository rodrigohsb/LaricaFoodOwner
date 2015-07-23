package com.laricafood.owner.app.async;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laricafood.owner.app.activity.EditEstabelecimentoActivity;
import com.laricafood.owner.app.adapter.AccountAdapter;
import com.laricafood.owner.app.bean.Larica;
import com.laricafood.owner.app.bean.User;
import com.laricafood.owner.app.persistence.UserRepository;
import com.laricafood.owner.app.util.AlertUtils;
import com.laricafood.owner.app.util.Constants;

import java.util.List;

/**
 * Created by Rodrigo on 26/06/15.
 */
public class AccountAsyncTask extends AsyncTask<Void, Void, User>
{

    private ProgressDialog dialog;

    private Context ctx;

    private TextView accountState;
    private ImageView accountErrorImage;
    private TextView accountNoEstabelecimentoTitle;
    private TextView accountNoEstabelecimentoSubTitle;
    private TextView qntdEstabelecimentos;

    private ListView list;

    private AccountAdapter adapter;

    private SharedPreferences sharedPreferences;

    private Boolean isFirstTime;

    public AccountAsyncTask (Context ctx, TextView accountState, ImageView accountErrorImage, TextView accountNoEstabelecimentoTitle, TextView accountNoEstabelecimentoSubTitle, TextView qntdEstabelecimentos, ListView list)
    {
        this.ctx = ctx;
        this.list = list;
        this.accountState = accountState;
        this.accountErrorImage = accountErrorImage;
        this.qntdEstabelecimentos = qntdEstabelecimentos;
        this.accountNoEstabelecimentoTitle = accountNoEstabelecimentoTitle;
        this.accountNoEstabelecimentoSubTitle = accountNoEstabelecimentoSubTitle;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        isFirstTime = sharedPreferences.getBoolean(Constants.IS_FIRST_TIME_ACCOUNT_ACTIVITY, true);

    }

    @Override
    protected void onPreExecute ()
    {
        super.onPreExecute();
        dialog = new ProgressDialog(ctx);
        dialog.setMessage("Buscando informações . . .");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected User doInBackground (Void... params)
    {

        /* Busca o user no cache e no DB */
        User user = getUser();

        if (isFirstTime)
        {
            return user;
        }

        if (user == null)
        {
            //TODO Buscar no servidor e atualizar o cache e o DB
        }

        for (int i = 0 ; i < 100000000 ; i++)
        {

        }

        return user;
    }

    @Override
    protected void onPostExecute (final User user)
    {
        super.onPostExecute(user);

        dialog.dismiss();

        if (isFirstTime)
        {

            sharedPreferences.edit().putBoolean(Constants.IS_FIRST_TIME_ACCOUNT_ACTIVITY, false).apply();

            accountNoEstabelecimentoTitle.setVisibility(View.VISIBLE);
            accountNoEstabelecimentoTitle.setText("Não perca tempo.");
            accountNoEstabelecimentoSubTitle.setVisibility(View.VISIBLE);
            accountNoEstabelecimentoSubTitle.setText("Cadastre logo sua larica!");
        }
        else if (user != null)
        {
            fill(user);
        }
        else
        {
            accountErrorImage.setVisibility(View.VISIBLE);
        }
    }


    User getUser ()
    {

        String json = sharedPreferences.getString(Constants.USER, Constants.USER);

        if (json != null)
        {
            try
            {
                return new Gson().fromJson(json, User.class);
            } catch (Exception e)
            {
                //Deu ruim ao buscar no cache.
                e.printStackTrace();
            }
        }

        try
        {
            return UserRepository.getInstance(ctx).getUser();
        } catch (Exception e)
        {
            //Deu ruim ao buscar no DB.
            e.printStackTrace();
        }

        return null;
    }

    private void fill (User user)
    {
        final List<Larica> laricas = user.getLaricas();

        if (laricas == null)
        {
            accountErrorImage.setVisibility(View.VISIBLE);
        }
        else if (laricas.isEmpty())
        {
            accountNoEstabelecimentoTitle.setVisibility(View.VISIBLE);
            accountNoEstabelecimentoSubTitle.setVisibility(View.VISIBLE);
            qntdEstabelecimentos.setText("0");
        }
        else
        {
            qntdEstabelecimentos.setText(String.valueOf(laricas.size()));
            list.setVisibility(View.VISIBLE);
            adapter = new AccountAdapter(ctx, laricas);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick (AdapterView<?> parent, View view, int position, long id)
                {
                    Intent it = new Intent(ctx, EditEstabelecimentoActivity.class);
                    it.putExtra(Constants.LARICA, laricas.get(position));
                    ctx.startActivity(it);
                }
            });

            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
            {
                @Override
                public boolean onItemLongClick (AdapterView<?> parent, View view, final int position, long id)
                {
                    final Larica larica = laricas.get(position);

                    if (larica.getStatus() == 0)
                    {
                        DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener()
                        {
                            public void onClick (DialogInterface dialog, int id)
                            {
                                new LaricaChangeStatusAsyncTask(ctx, larica, dialog, adapter).execute();
                            }
                        };
                        DialogInterface.OnClickListener negativeButton = new DialogInterface.OnClickListener()
                        {
                            public void onClick (DialogInterface dialog, int id)
                            {
                                dialog.dismiss();
                            }
                        };

                        AlertDialog dialog;
                        if (larica.isOpen())
                        {
                            dialog = new AlertUtils(ctx).getAlertDialog("Fechar larica ?", null, true, positiveButton, "Sim", negativeButton, "Não");
                        }
                        else
                        {
                            dialog = new AlertUtils(ctx).getAlertDialog("Abrir larica ?", null, true, positiveButton, "Sim", negativeButton, "Não");
                        }

                        dialog.show();
                    }
                    else
                    {
                        DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener()
                        {
                            public void onClick (DialogInterface dialog, int id)
                            {
                                dialog.dismiss();
                            }
                        };
                        new AlertUtils(ctx).getAlertDialog("Só é possível alterar abrir ou fechar laricas ativas.", null, true, positiveButton, "Ok", null, null).show();
                    }
                    return true;
                }
            });

        }
    }
}
