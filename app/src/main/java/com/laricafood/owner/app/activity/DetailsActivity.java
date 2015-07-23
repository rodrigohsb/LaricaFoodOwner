package com.laricafood.owner.app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.appevents.AppEventsLogger;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.async.VoteAsyncTask;
import com.laricafood.owner.app.bean.Larica;
import com.laricafood.owner.app.util.AlertUtils;
import com.laricafood.owner.app.util.ConnectionUtils;

/**
 * Created by Rodrigo on 16/05/15.
 */
public class DetailsActivity extends AppCompatActivity
{

    private Context ctx;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ctx = this;

        TextView title = (TextView) findViewById(R.id.detailsTitle);
        TextView address = (TextView) findViewById(R.id.detailsAddress);
        ImageView statusIcon = (ImageView) findViewById(R.id.detailsStatusIcon);
        TextView statusValue = (TextView) findViewById(R.id.detailsStatusValue);
        TextView detailsOwner = (TextView) findViewById(R.id.detailsOwner);
        TextView distance = (TextView) findViewById(R.id.detailsDistance);
        TextView like = (TextView) findViewById(R.id.detailsLike);

        Bundle bundle = getIntent().getExtras();
        Larica larica = (Larica) bundle.getSerializable("larica");
        title.setText(larica.getNome());

        StringBuilder addressBuilder = new StringBuilder(larica.getEndereco()).append(" ").append(larica.getNumber()).append(" - ").append(larica.getBairro()).append(" / ").append(larica.getUf());

        address.setText(addressBuilder.toString());


        if (larica.isOpen())
        {
            statusIcon.setImageResource(R.drawable.ic_open2);
            statusValue.setText(" Aberto");
        }
        else
        {
            statusIcon.setImageResource(R.drawable.ic_closed2);
            statusValue.setText(" Fechado");
        }

        if (larica.getDistance() > 1)
        {
            distance.setText(" " + larica.getDistance() + " Km");
        }
        else
        {
            distance.setText(" " + larica.getDistance() + " m");
        }

        like.setText(" " + larica.getLike() + " %");

        detailsOwner.setText(" " + larica.getUser().getFirstName() + " " + larica.getUser().getLastName());

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_like:
            case R.id.action_unlike:

                if (ConnectionUtils.isOnline(ctx))
                {

                    final View view = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.dialog_rating, null);
                    DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener()
                    {
                        public void onClick (DialogInterface dialog, int id)
                        {

                            final RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

                            final View confirmationView = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.dialog_rating_confirmation, null);
                            final TextView title = (TextView) confirmationView.findViewById(R.id.confirmationDialogTitle);

                            title.setText("Sua nota foi " + ratingBar.getRating() + " . Confirmar?");

                            AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(DetailsActivity.this);
                            confirmationDialog.setCancelable(false).setView(confirmationView).setPositiveButton("Confirmar", new DialogInterface.OnClickListener()
                            {
                                public void onClick (DialogInterface dialog, int id)
                                {

                                    //TODO Não permitir que proprietário vote na sua própria larica.
                                    new VoteAsyncTask(ctx).execute();
                                    dialog.dismiss();

                                }
                            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                            {
                                public void onClick (DialogInterface dialog, int id)
                                {
                                    dialog.dismiss();
                                }
                            });
                            confirmationDialog.create().show();
                        }
                    };
                    DialogInterface.OnClickListener negativeButton = new DialogInterface.OnClickListener()
                    {
                        public void onClick (DialogInterface dialog, int id)
                        {
                            dialog.dismiss();
                        }
                    };

                    new AlertUtils(ctx).getAlertDialog(view, null, null, false, positiveButton, "Confirmar", negativeButton, "Cancelar").show();

                }
                else
                {
                    ((Activity) ctx).runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run ()
                        {
                            Toast.makeText(ctx.getApplicationContext(), "Nenhuma conexão ativa foi encontrada.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause ()
    {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

}
