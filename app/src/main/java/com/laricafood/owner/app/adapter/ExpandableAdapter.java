package com.laricafood.owner.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.laricafood.owner.app.R;

import java.util.ArrayList;

/**
 * Created by Rodrigo on 12/07/15.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter
{

    private ArrayList<Object> childtems;
    private ArrayList<String> parentItems;
    private ArrayList<String> child;
    private static LayoutInflater inflater = null;

    public ExpandableAdapter (Context ctx, ArrayList<String> parents, ArrayList<Object> childern)
    {
        this.parentItems = parents;
        this.childtems = childern;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getChildView (int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {

        child = (ArrayList<String>) childtems.get(groupPosition);

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.activity_terms_child_expandablelist, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.textView1);

        textView.setText(child.get(childPosition));

        return convertView;
    }

    @Override
    public View getGroupView (int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.activity_terms_parent_expandablelist, null);
        }

        ((CheckedTextView) convertView).setText(parentItems.get(groupPosition));
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public Object getChild (int groupPosition, int childPosition)
    {
        return null;
    }

    @Override
    public long getChildId (int groupPosition, int childPosition)
    {
        return 0;
    }

    @Override
    public int getChildrenCount (int groupPosition)
    {
        return ((ArrayList<String>) childtems.get(groupPosition)).size();
    }


    @Override
    public Object getGroup (int groupPosition)
    {
        return null;
    }


    @Override
    public int getGroupCount ()
    {
        return parentItems.size();
    }


    @Override

    public void onGroupCollapsed (int groupPosition)
    {
        super.onGroupCollapsed(groupPosition);
    }


    @Override
    public void onGroupExpanded (int groupPosition)
    {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId (int groupPosition)
    {
        return 0;
    }

    @Override
    public boolean hasStableIds ()
    {
        return false;
    }

    @Override
    public boolean isChildSelectable (int groupPosition, int childPosition)
    {
        return false;
    }
}
