package com.laricafood.owner.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.laricafood.owner.app.R;
import com.laricafood.owner.app.adapter.ExpandableAdapter;

import java.util.ArrayList;

/**
 * Created by Rodrigo on 12/07/15.
 */
public class TermsActivity extends AppCompatActivity
{

    private ArrayList<String> parentItems = new ArrayList<>();
    private ArrayList<Object> childItems = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        ExpandableListView expandableList = (ExpandableListView) findViewById(R.id.titleExpandableListView);

        expandableList.setDividerHeight(2);
        expandableList.setGroupIndicator(null);
        expandableList.setClickable(true);

        fill();

        ExpandableAdapter adapter = new ExpandableAdapter(this, parentItems, childItems);

        expandableList.setAdapter(adapter);

    }

    public void fill ()
    {
        parentItems.add("1 - O que é?");
        ArrayList<String> child = new ArrayList<>();
        child.add("Blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá blá blá blá blá blá blá blá blá.");
        childItems.add(child);

        parentItems.add("2 - Pra que serve?");
        child = new ArrayList<>();
        child.add("Blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá.\n\nBlá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.\n\nBlá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.");
        childItems.add(child);

        parentItems.add("3 - Preço");
        child = new ArrayList<>();
        child.add("Blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.");
        childItems.add(child);

        parentItems.add("4 - Disposições Gerais");
        child = new ArrayList<>();
        child.add("Blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá.\n\nBlá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                " blá blá blá blá blá blá blá blá.\n\nBlá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.");
        childItems.add(child);
    }

    @Override
    public void onBackPressed ()
    {
        finish();
    }
}
