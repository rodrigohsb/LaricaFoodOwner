package com.laricafood.owner.app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.async.NewLaricaAsyncTask;
import com.laricafood.owner.app.util.AlertUtils;

/**
 * Created by Rodrigo on 01/03/15.
 */
public class NewEstabelecimentoActivity extends AppCompatActivity
{
    private Context ctx;

    private static final int CAPTURE_IMAGE_ONE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_IMAGE_TWO_ACTIVITY_REQUEST_CODE = 200;
    private static final int CAPTURE_IMAGE_THREE_ACTIVITY_REQUEST_CODE = 300;
    private static final int CAPTURE_IMAGE_FOUR_ACTIVITY_REQUEST_CODE = 400;
    private static final int CAPTURE_IMAGE_FIVE_ACTIVITY_REQUEST_CODE = 500;

    private ImageView pictureOne;
    private ImageView pictureTwo;
    private ImageView pictureThree;
    private ImageView pictureFour;
    private ImageView pictureFive;

    private TextView newEstabelecimentoTitle;
    private TextView newEstabelecimentoAddress;
    private TextView newEstabelecimentoNumber;
    private TextView newEstabelecimentoBairro;
    private Spinner newEstabelecimentoSpinner;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ctx = this;
        FacebookSdk.sdkInitialize(ctx);

        setContentView(R.layout.activity_new_estabelecimento);

        pictureOne = (ImageView) findViewById(R.id.newEstabelecimentoPictureOne);
        pictureTwo = (ImageView) findViewById(R.id.newEstabelecimentoPictureTwo);
        pictureThree = (ImageView) findViewById(R.id.newEstabelecimentoPictureThree);
        pictureFour = (ImageView) findViewById(R.id.newEstabelecimentoPictureFour);
        pictureFive = (ImageView) findViewById(R.id.newEstabelecimentoPictureFive);

        newEstabelecimentoTitle = (TextView) findViewById(R.id.newEstabelecimentoTitle);
        newEstabelecimentoAddress = (TextView) findViewById(R.id.newEstabelecimentoAddress);
        newEstabelecimentoNumber = (TextView) findViewById(R.id.newEstabelecimentoNumber);
        newEstabelecimentoBairro = (TextView) findViewById(R.id.newEstabelecimentoBairro);
        newEstabelecimentoSpinner = (Spinner) findViewById(R.id.newEstabelecimentoSpinner);


        pictureOne.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivityForResult(new Intent(ctx, CameraActivity.class), CAPTURE_IMAGE_ONE_ACTIVITY_REQUEST_CODE);
            }
        });

        pictureTwo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivityForResult(new Intent(ctx, CameraActivity.class), CAPTURE_IMAGE_TWO_ACTIVITY_REQUEST_CODE);
            }
        });

        pictureThree.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivityForResult(new Intent(ctx, CameraActivity.class), CAPTURE_IMAGE_THREE_ACTIVITY_REQUEST_CODE);
            }
        });

        pictureFour.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivityForResult(new Intent(ctx, CameraActivity.class), CAPTURE_IMAGE_FOUR_ACTIVITY_REQUEST_CODE);
            }
        });

        pictureFive.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivityForResult(new Intent(ctx, CameraActivity.class), CAPTURE_IMAGE_FIVE_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_estabelecimento, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {

        final View view = LayoutInflater.from(NewEstabelecimentoActivity.this).inflate(R.layout.dialog_new_estabelecimento, null);
        TextView title = (TextView) view.findViewById(R.id.dialogNewEstabelecimentoTitle);

        AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(ctx).setView(view).setCancelable(false);

        switch (item.getItemId())
        {
            case R.id.action_cancel:

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
                break;

            case R.id.action_save:

                title.setText("Deseja salvar?");

                confirmationDialog.setPositiveButton("Salvar", new DialogInterface.OnClickListener()
                {
                    public void onClick (DialogInterface dialog, int id)
                    {
                        dialog.dismiss();
                        if (validate())
                        {
                            new NewLaricaAsyncTask().execute();
                        }
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                {
                    public void onClick (DialogInterface dialog, int id)
                    {
                        dialog.dismiss();
                    }
                });
                confirmationDialog.create().show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private boolean validate ()
    {

        DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener()
        {
            public void onClick (DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        };

        if (pictureOne.getDrawable() == null)
        {
            new AlertUtils(ctx).getAlertDialog("Falta imagem 1.", null, true, positiveButton, "Ok", null, null).show();
            return false;
        }
        else if (pictureTwo.getDrawable() == null)
        {
            new AlertUtils(ctx).getAlertDialog("Falta imagem 2.", null, true, positiveButton, "Ok", null, null).show();
            return false;
        }
        else if (pictureThree.getDrawable() == null)
        {
            new AlertUtils(ctx).getAlertDialog("Falta imagem 3.", null, true, positiveButton, "Ok", null, null).show();
            return false;
        }
        else if (pictureFour.getDrawable() == null)
        {
            new AlertUtils(ctx).getAlertDialog("Falta imagem 4.", null, true, positiveButton, "Ok", null, null).show();
            return false;
        }
        else if (pictureFive.getDrawable() == null)
        {
            new AlertUtils(ctx).getAlertDialog("Falta imagem 5.", null, true, positiveButton, "Ok", null, null).show();
            return false;
        }
        else if (newEstabelecimentoTitle.getText().toString() == null || "".equals(newEstabelecimentoTitle.getText().toString().trim()))
        {
            newEstabelecimentoTitle.setError("Por favor, preencha o nome.");
            return false;
        }
        else if (newEstabelecimentoAddress.getText().toString() == null || "".equals(newEstabelecimentoAddress.getText().toString().trim()))
        {
            newEstabelecimentoAddress.setError("Por favor, preencha o endereco.");
            return false;
        }
        else if (newEstabelecimentoNumber.getText().toString() == null || "".equals(newEstabelecimentoNumber.getText().toString().trim()))
        {
            newEstabelecimentoNumber.setError("Por favor, preencha o numero.");
            return false;
        }
        else if (newEstabelecimentoBairro.getText().toString() == null || "".equals(newEstabelecimentoBairro.getText().toString().trim()))
        {
            newEstabelecimentoBairro.setError("Por favor, preencha o bairro.");
            return false;
        }
        return true;
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

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAPTURE_IMAGE_ONE_ACTIVITY_REQUEST_CODE)
        {
            addImage(pictureOne, resultCode, data);
        }

        else if (requestCode == CAPTURE_IMAGE_TWO_ACTIVITY_REQUEST_CODE)
        {
            addImage(pictureTwo, resultCode, data);
        }

        else if (requestCode == CAPTURE_IMAGE_THREE_ACTIVITY_REQUEST_CODE)
        {
            addImage(pictureThree, resultCode, data);
        }

        else if (requestCode == CAPTURE_IMAGE_FOUR_ACTIVITY_REQUEST_CODE)
        {
            addImage(pictureFour, resultCode, data);
        }

        else if (requestCode == CAPTURE_IMAGE_FIVE_ACTIVITY_REQUEST_CODE)
        {
            addImage(pictureFive, resultCode, data);
        }
    }

    private void addImage (ImageView pictureView, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            if (data == null)
            {
                ((Activity) ctx).runOnUiThread(new Runnable()
                {
                    @Override
                    public void run ()
                    {
                        Toast.makeText(ctx, "data is null!", Toast.LENGTH_LONG).show();
                    }
                });
                return;
            }

            pictureView.setImageURI(data.getData());

        }
        else if (resultCode != RESULT_CANCELED)
        {
            ((Activity) ctx).runOnUiThread(new Runnable()
            {
                @Override
                public void run ()
                {
                    Toast.makeText(ctx, "Ocorreu um erro ao capturar imagem", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
