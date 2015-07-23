package com.laricafood.owner.app.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.laricafood.owner.app.activity.DetailsActivity;
import com.laricafood.owner.app.adapter.ResultListAdapter;
import com.laricafood.owner.app.bean.Category;
import com.laricafood.owner.app.bean.Larica;
import com.laricafood.owner.app.interfaces.RecyclerVewOnCLickListenerHack;
import com.laricafood.owner.app.teste.Teste;
import com.laricafood.owner.app.util.ConnectionUtils;
import com.laricafood.owner.app.util.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Rodrigo on 26/06/15.
 */
public class LaricasAsyncTask extends AsyncTask<Void, Void, List<Larica>> implements RecyclerVewOnCLickListenerHack
{

    private Context ctx;

    private Category category;
    private ImageView resultErrorImage;
    private TextView resultNoEstabelecimentos;

    private RecyclerView recyclerView;

    private ProgressDialog dialog;

    private SharedPreferences sharedPreferences;

    private List<Larica> laricas;

    public LaricasAsyncTask (Context ctx, Category category, ImageView resultErrorImage, TextView resultNoEstabelecimentos, RecyclerView recyclerView)
    {
        this.ctx = ctx;
        this.category = category;
        this.resultErrorImage = resultErrorImage;
        this.resultNoEstabelecimentos = resultNoEstabelecimentos;
        this.recyclerView = recyclerView;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    @Override
    protected void onPreExecute ()
    {
        super.onPreExecute();
        dialog = new ProgressDialog(ctx);
        dialog.setMessage("Buscando informações...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected List<Larica> doInBackground (Void... params)
    {

        //1 - Se o cache estiver recente, pega nele
        //2 - Senão busca no server
        //3 - Se deu merda no server, pega o cache mesmo (Fazer o que né?)

        /* Busca no cache recente */
        try
        {
            //TODO Pegar no cache de acordo com a category
            String lastUpdateInCache = sharedPreferences.getString(Constants.LAST_UPDATE_LARICAS_HAMBURGUER, Constants.LAST_UPDATE_LARICAS_HAMBURGUER);

            if (lastUpdateInCache != null)
            {
                DateFormat dateFormat = new SimpleDateFormat(Constants.LAST_UPDATE_PATTERN);

                Date lastUpdate = dateFormat.parse(lastUpdateInCache);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(lastUpdate);
                //TODO Avaliar um prazo pra pegar no cache(10 min, 1 dia, 1 semana,...?)
                calendar.add(Calendar.HOUR, 1);

                if (lastUpdate.after(new Date()))
                {
                    List<Larica> laricas = new ArrayList<>();

                    String json = sharedPreferences.getString(Constants.LARICAS, Constants.LARICAS);

                    if (json != null)
                    {
                        laricas = new Gson().fromJson(json, new TypeToken<List<Larica>>()
                        {
                        }.getType());
                    }
                    return laricas;
                }
            }
        } catch (ParseException e)
        {
            //Deu merda no format
            e.printStackTrace();
        } catch (Exception e)
        {
            //Deu alguma merda
            e.printStackTrace();
        }

        if (ConnectionUtils.isOnline(ctx))
        {
            /* Busca no servidor */
            try
            {
                return getLaricasOnServer();
            } catch (Exception e)
            {
                //Deu merda ao buscar no server
                e.printStackTrace();
            }
        }

        /* Busca no cache */
        try
        {
            List<Larica> laricas = new ArrayList<>();

            String json = sharedPreferences.getString(Constants.LARICAS, Constants.LARICAS);

            if (json != null)
            {
                laricas = new Gson().fromJson(json, new TypeToken<List<Larica>>()
                {
                }.getType());
            }

            if (laricas != null && !laricas.isEmpty())
            {
                if (Constants.LOCATION != null)
                {
                    for (Larica larica : laricas)
                    {
                        //TODO Calcular distancia
                    }
                }
                else
                {
                    for (Larica larica : laricas)
                    {
                        larica.setDistance(0);
                    }
                }
                return laricas;
            }
        } catch (Exception e)
        {
            //Deu alguma merda
            e.printStackTrace();
        }

        //Não consegui pegar as laricas de jeito nenhum. Que merda!
        return null;
    }

    private List<Larica> getLaricasOnServer ()
    {
        if (Constants.LOCATION != null)
        {
            //TODO Passar o location (Constants.LOCATION) e a category    
        }
        else
        {
            //TODO Passar a category
            //TODO Chegar o location na volta. Se tiver preenchido, calcular distancia.
        }

        for (int i = 0 ; i < 100000000 ; i++)
        {

        }

        //TODO Colocar no cache
        return Teste.getEstabelecimentos();
    }

    @Override
    protected void onPostExecute (final List<Larica> laricas)
    {
        super.onPostExecute(laricas);

        dialog.dismiss();

        if (laricas == null)
        {
            resultErrorImage.setVisibility(View.VISIBLE);
        }
        else if (laricas.isEmpty())
        {
            resultNoEstabelecimentos.setVisibility(View.VISIBLE);
        }
        else
        {
            recyclerView.setVisibility(View.VISIBLE);

            this.laricas = laricas;

            LinearLayoutManager llm = new LinearLayoutManager(ctx);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);

            ResultListAdapter adapter = new ResultListAdapter(ctx, laricas);
            adapter.setRecyclerVewOnCLickListenerHack(this);
            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    public void onClickListener (View v, int position)
    {
        Intent it = new Intent(ctx, DetailsActivity.class);
        it.putExtra("larica", laricas.get(position));
        ctx.startActivity(it);
    }
}