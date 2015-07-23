package com.laricafood.owner.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.laricafood.owner.app.R;
import com.laricafood.owner.app.async.NewAccountAsyncTask;
import com.laricafood.owner.app.bean.Type;

/**
 * Created by Rodrigo on 07/07/15.
 */
public class NewAccountActivity extends AppCompatActivity
{

    private Context ctx;
    private RadioButton cliente;
    private RadioButton comerciante;
    private TextView terms;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        ctx = this;

        cliente = (RadioButton) findViewById(R.id.radioButtonPerfilCliente);
        comerciante = (RadioButton) findViewById(R.id.radioButtonPerfilComerciante);
        terms = (TextView) findViewById(R.id.newAccountTerms);

        final Button buttonNewAccount = (Button) findViewById(R.id.buttonNewAccount);

        cliente.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    buttonNewAccount.setText("Quero entrar!");
                }
            }
        });

        comerciante.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    buttonNewAccount.setText("Quero criar minha conta!");
                }
            }
        });

        buttonNewAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (!cliente.isChecked() && !comerciante.isChecked())
                {
                    ((Activity) ctx).runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run ()
                        {
                            Toast.makeText(ctx.getApplicationContext(), "Nenhum perfil foi selecionado", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    if (cliente.isChecked())
                    {
                        new NewAccountAsyncTask(Type.CLIENTE, ctx).execute();
                    }
                    else
                    {
                        startActivity(new Intent(ctx, PaymentActivity.class));
                    }
                }
            }
        });

        terms.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(ctx, TermsActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed ()
    {
        //TODO Marcar como perfil pendente.
        finish();
    }
}
