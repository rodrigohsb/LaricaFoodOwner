package com.laricafood.owner.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.laricafood.owner.app.R;

/**
 * Created by Rodrigo on 05/07/15.
 */
public class CameraPreviewActivity extends Activity
{

    ImageView cameraPreview;
    ImageView cameraPreviewCancel;
    ImageView cameraPreviewAccept;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera_preview);

        cameraPreview = (ImageView) findViewById(R.id.cameraPreview);

        cameraPreview.setImageURI(getIntent().getData());

        cameraPreviewCancel = (ImageView) findViewById(R.id.cameraPreviewCancel);
        cameraPreviewAccept = (ImageView) findViewById(R.id.cameraPreviewAccept);

        cameraPreviewCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        });

        cameraPreviewAccept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }
}
