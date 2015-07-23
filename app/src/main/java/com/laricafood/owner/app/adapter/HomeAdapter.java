package com.laricafood.owner.app.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laricafood.owner.app.R;
import com.laricafood.owner.app.bean.Category;
import com.laricafood.owner.app.util.ImageLoader;

import java.util.List;

/**
 * Created by Rodrigo on 17/05/15.
 */
public class HomeAdapter extends BaseAdapter
{
    private Context ctx;
    private List<Category> categories;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public HomeAdapter (Context ctx, List<Category> data)
    {
        this.categories = data;
        this.ctx = ctx;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageLoader = new ImageLoader(ctx.getApplicationContext());
    }

    @Override
    public int getCount ()
    {
        return categories.size();
    }

    @Override
    public Object getItem (int position)
    {
        return null;
    }

    @Override
    public long getItemId (int position)
    {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;

        ViewHolder holder;

        if (convertView == null)
        {
            holder = new ViewHolder();

            vi = inflater.inflate(R.layout.row_home, null);

            holder.image = (ImageView) vi.findViewById(R.id.homeListRowImage);
            holder.title = (TextView) vi.findViewById(R.id.homeListRowTitle);

            vi.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        Category category = categories.get(position);

        Drawable drawable = getDrawable(category.getResourceId());

        holder.image.setImageDrawable(drawable);
        holder.title.setText(category.getName());

        return vi;
    }

    private static class ViewHolder
    {
        ImageView image;
        TextView title;
    }

    public Drawable getDrawable (int resourceId)
    {
        return ctx.getResources().getDrawable(resourceId);
    }
}
