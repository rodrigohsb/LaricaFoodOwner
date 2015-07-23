package com.laricafood.owner.app.activity;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Rodrigo on 01/07/15.
 */
public class CameraLive extends SurfaceView implements SurfaceHolder.Callback
{
    private Camera camera;

    private SurfaceHolder holder;


    public CameraLive (Context context, Camera camera)
    {
        super(context);
        this.camera = camera;

        this.holder = getHolder();

        // Install a SurfaceHolder.Callback so we get notified when the underlying surface is created and destroyed.
        holder.addCallback(this);

        // deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated (SurfaceHolder holder)
    {
        // The Surface has been created, now tell the camera where to draw the preview.
        try
        {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }
        catch (IOException e)
        {
            Log.d("CameraPreview", "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged (SurfaceHolder holder, int format, int width, int height)
    {
        refreshCamera(camera);
    }

    @Override
    public void surfaceDestroyed (SurfaceHolder holder)
    {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    public void refreshCamera (Camera camera)
    {
        if (holder.getSurface() == null)
        {
            // preview surface does not exist
            return;
        }
        // stop preview before making changes
        try
        {
            camera.stopPreview();
        }
        catch (Exception e)
        {
            // ignore: tried to stop a non-existent preview
        }
        // set preview size and make any resize, rotate or reformatting changes here start preview with new settings.
        camera.setDisplayOrientation(90);
        setCamera(camera);

        try
        {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }
        catch (Exception e)
        {
            Log.d("CameraPreview", "Error starting camera preview: " + e.getMessage());
        }
    }

    public void setCamera (Camera camera)
    {
        this.camera = camera;
    }


}
