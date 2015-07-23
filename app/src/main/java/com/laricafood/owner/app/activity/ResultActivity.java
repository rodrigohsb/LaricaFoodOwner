package com.laricafood.owner.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.async.LaricasAsyncTask;
import com.laricafood.owner.app.bean.Category;
import com.laricafood.owner.app.util.Constants;

/**
 * Created by Rodrigo on 01/03/15.
 */
public class ResultActivity extends AppCompatActivity
{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_result);
        Context ctx = this;

        ImageView resultErrorImage = (ImageView) findViewById(R.id.resultErrorImage);
//        ListView resultList = (ListView) findViewById(R.id.resultList);
        TextView resultNoEstabelecimentos = (TextView) findViewById(R.id.resultNoEstabelecimentos);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        
        Bundle bundle = getIntent().getExtras();
        Category category = (Category) bundle.getSerializable(Constants.CATEGORY);

        new LaricasAsyncTask(ctx, category, resultErrorImage, resultNoEstabelecimentos, mRecyclerView).execute();

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
