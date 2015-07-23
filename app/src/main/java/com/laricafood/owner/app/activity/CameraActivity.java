package com.laricafood.owner.app.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.laricafood.owner.app.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Rodrigo on 02/07/15.
 */
public class CameraActivity extends AppCompatActivity
{

    private Camera mCamera;
    private CameraLive cameraPreview;

    private Context ctx;

    private ImageView flashCameraOn;
    private ImageView flashCameraOff;

    private static int currentCamera;

    private Camera.Parameters params;

    private File pictureFile;

    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        currentCamera = Camera.CameraInfo.CAMERA_FACING_BACK;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        buildActionBar();

        setContentView(R.layout.activity_camera);

        ctx = this;

        ImageView buttonCapture = (ImageView) findViewById(R.id.buttonCapture);
        flashCameraOn = (ImageView) findViewById(R.id.cameraFlashOn);
        flashCameraOff = (ImageView) findViewById(R.id.cameraFlashOff);

        initializeCamera();

        buildUIButtons();

        /*
        changeCamera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                mCamera.release();
                mCamera = changeCurrentCamera();
                buildUIButtons();
                cameraPreview.refreshCamera(mCamera);
            }
        });
        */
        
        buttonCapture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                mCamera.takePicture(null, null, pictureCallback);
            }
        });

        flashCameraOn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                flashCameraOn.setVisibility(View.GONE);
                flashCameraOff.setVisibility(View.VISIBLE);
                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(params);
            }
        });

        flashCameraOff.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                flashCameraOff.setVisibility(View.GONE);
                flashCameraOn.setVisibility(View.VISIBLE);
                params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                mCamera.setParameters(params);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
        refreshCamera();
    }

    @Override
    protected void onPause ()
    {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera ()
    {
        if (mCamera != null)
        {
            mCamera.release();
            mCamera = null;
        }
    }

    private void initializeCamera ()
    {
        mCamera = getCameraInstance(currentCamera);
        cameraPreview = new CameraLive(ctx, mCamera);
        FrameLayout frame = (FrameLayout) findViewById(R.id.cameraFrame);
        frame.addView(cameraPreview);
        params = mCamera.getParameters();
    }

    private void refreshCamera ()
    {
        if (mCamera == null)
        {
            mCamera = Camera.open(currentCamera);
            cameraPreview.refreshCamera(mCamera);
        }
    }

    @Override
    public void onBackPressed ()
    {
        releaseCamera();
        finish();
    }

    public static Camera getCameraInstance (int camId)
    {
        try
        {
            return Camera.open(camId);
        } catch (Exception e)
        {
            Log.i("DDD", "Erro : " + e.getMessage());
            return null;
        }
    }

    private static Camera changeCurrentCamera ()
    {
        Camera camera;
        if (currentCamera == Camera.CameraInfo.CAMERA_FACING_BACK)
        {
            camera = getCameraInstance(Camera.CameraInfo.CAMERA_FACING_FRONT);

            if (camera != null)
            {
                currentCamera = Camera.CameraInfo.CAMERA_FACING_FRONT;
            }
        }
        else
        {
            camera = getCameraInstance(Camera.CameraInfo.CAMERA_FACING_BACK);

            if (camera != null)
            {
                currentCamera = Camera.CameraInfo.CAMERA_FACING_BACK;
            }
        }
        return camera;

    }

    private void buildUIButtons ()
    {
        if (hasFlash())
        {
            flashCameraOn.setVisibility(View.VISIBLE);
            params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            mCamera.setParameters(params);
        }
        /*
        if (Camera.getNumberOfCameras() > 1)
        {
            changeCamera.setVisibility(View.VISIBLE);
        }
        */
    }

    private boolean hasFlash ()
    {
        Camera.Parameters params = mCamera.getParameters();

        List<String> flashModes = params.getSupportedFlashModes();

        if (flashModes == null)
        {
            return false;
        }

        for (String flashMode : flashModes)
        {
            if (Camera.Parameters.FLASH_MODE_ON.equals(flashMode))
            {
                return true;
            }
        }
        return false;
    }

    private void buildActionBar ()
    {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        getSupportActionBar().setIcon(R.drawable.ic_action_cancel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_camera_activity);
    }

    private PictureCallback pictureCallback = new PictureCallback()
    {
        @Override
        public void onPictureTaken (byte[] data, Camera camera)
        {

            pictureFile = getOutputMediaFile();

            if (pictureFile == null)
            {
                Log.d("CameraActivity", "Error creating media file, check storage permissions.");
                return;
            }

            try
            {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();

                Intent it = new Intent(ctx, CameraPreviewActivity.class);
                it.setData(Uri.fromFile(pictureFile));
                startActivityForResult(it, 1000);

            } 
            catch (FileNotFoundException e)
            {
                Log.d("CameraActivity", "File not found: " + e.getMessage());
            } 
            catch (IOException e)
            {
                Log.d("CameraActivity", "Error accessing file: " + e.getMessage());
            }
        }
    };

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            Intent resultIntent = new Intent();
            resultIntent.setData(Uri.fromFile(pictureFile));
            setResult(RESULT_OK, resultIntent);
            finish();
        }
        else if (resultCode == RESULT_CANCELED)
        {
            //TODO Reabrir a câmera!
        }
        else
        {
            //TODO Ver o que deve ser mostrado!
        }
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile ()
    {
        // To be safe, you should check that the SDCard is mounted using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "LaricaFood");
        // This location works best if you want the created images to be shared between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists())
        {
            if (!mediaStorageDir.mkdirs())
            {
                Log.d("LaricaFood", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }
}
