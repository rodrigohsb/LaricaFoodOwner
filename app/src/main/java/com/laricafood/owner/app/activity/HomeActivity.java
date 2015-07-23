package com.laricafood.owner.app.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.adapter.HomeAdapter;
import com.laricafood.owner.app.bean.Category;
import com.laricafood.owner.app.teste.Teste;
import com.laricafood.owner.app.util.Constants;

import java.util.List;


public class HomeActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{

    private Context ctx;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ctx = this;
        buildGoogleApiClient();
        setContentView(R.layout.activity_home);

        GridView gridView = (GridView) findViewById(R.id.homeGrid);

        final List<Category> categories = Teste.getCategories();

        gridView.setAdapter(new HomeAdapter(this, categories));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id)
            {
                Intent myIntent = new Intent(ctx, ResultActivity.class);
                myIntent.putExtra(Constants.CATEGORY, categories.get(position));
                startActivity(myIntent);
            }
        });
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
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_account:
                startActivity(new Intent(ctx, AccountActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed ()
    {
        googleApiClient.disconnect();
        finish();
    }

    protected synchronized void buildGoogleApiClient ()
    {
        googleApiClient = new GoogleApiClient.Builder(ctx).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        googleApiClient.connect();
    }

    @Override
    protected void onStop ()
    {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected (Bundle bundle)
    {
        Constants.LOCATION = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }

    @Override
    public void onConnectionSuspended (int i)
    {

    }

    @Override
    public void onConnectionFailed (ConnectionResult connectionResult)
    {

    }
}