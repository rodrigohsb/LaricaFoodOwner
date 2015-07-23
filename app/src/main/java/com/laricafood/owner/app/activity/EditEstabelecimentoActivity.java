package com.laricafood.owner.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.bean.Larica;
import com.laricafood.owner.app.util.Constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rodrigo on 01/03/15.
 */
public class EditEstabelecimentoActivity extends AppCompatActivity
{

    private Larica larica;

    List<String> ufs;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_estabelecimento);

        Bundle bundle = getIntent().getExtras();
        larica = (Larica) bundle.getSerializable(Constants.LARICA);

        EditText nome = (EditText) findViewById(R.id.editEstabelecimentoNome);
        EditText endereco = (EditText) findViewById(R.id.editEstabelecimentoEndereco);
        EditText numero = (EditText) findViewById(R.id.editEstabelecimentoNumero);
        EditText bairro = (EditText) findViewById(R.id.editEstabelecimentoBairro);
        Spinner uf = (Spinner) findViewById(R.id.spinnerEstabelecimentoUf);

        nome.setText(larica.getNome());
        endereco.setText(larica.getEndereco());
        numero.setText(larica.getNumber());
        bairro.setText(larica.getBairro());
        ufs = Arrays.asList(getResources().getStringArray(R.array.ufAux));
        uf.setSelection(ufs.indexOf(larica.getUf()));
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_estabelecimento, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (final MenuItem item)
    {

        if (item.getItemId() == R.id.action_trash)
        {
            final View view = LayoutInflater.from(EditEstabelecimentoActivity.this).inflate(R.layout.dialog_edit_estabelecimento, null);
            TextView title = (TextView) view.findViewById(R.id.dialogEditEstabelecimentoTitle);

            AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(EditEstabelecimentoActivity.this).setView(view).setCancelable(false);

            title.setText("Tem certeja que deseja deletar o(a) " + larica.getNome() + " ?");

            confirmationDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener()
            {
                public void onClick (DialogInterface dialog, int id)
                {
                    //TODO Deletar larica
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
        else if (item.getItemId() == R.id.action_cancel)
        {
            final View view = LayoutInflater.from(EditEstabelecimentoActivity.this).inflate(R.layout.dialog_edit_estabelecimento, null);
            TextView title = (TextView) view.findViewById(R.id.dialogEditEstabelecimentoTitle);

            AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(EditEstabelecimentoActivity.this).setView(view).setCancelable(false);

            title.setText("Todas as alterações serão perdidas. Deseja continuar?");

            confirmationDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener()
            {
                public void onClick (DialogInterface dialog, int id)
                {
                    dialog.dismiss();
                    finish();
                }
            }).setNegativeButton("Não", new DialogInterface.OnClickListener()
            {
                public void onClick (DialogInterface dialog, int id)
                {
                    dialog.dismiss();
                }
            });
            confirmationDialog.create().show();
        }
        else if (item.getItemId() == R.id.action_save)
        {
            final View view = LayoutInflater.from(EditEstabelecimentoActivity.this).inflate(R.layout.dialog_edit_estabelecimento, null);
            TextView title = (TextView) view.findViewById(R.id.dialogEditEstabelecimentoTitle);

            AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(EditEstabelecimentoActivity.this).setView(view).setCancelable(false);

            //TODO Mostrar como ficaria

            title.setText("Deseja salvar?");

            confirmationDialog.setPositiveButton("Salvar", new DialogInterface.OnClickListener()
            {
                public void onClick (DialogInterface dialog, int id)
                {
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

    @Override
    public void onBackPressed ()
    {
        finish();
    }
}
