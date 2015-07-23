package com.laricafood.owner.app.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.bean.Larica;
import com.laricafood.owner.app.interfaces.RecyclerVewOnCLickListenerHack;

import java.util.List;

/**
 * Created by Rodrigo on 01/03/15.
 */
public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolder>
{

    private List<Larica> laricas;
    private LayoutInflater inflater;
    private RecyclerVewOnCLickListenerHack recyclerVewOnCLickListenerHack;

    public ResultListAdapter (Context ctx, List<Larica> laricas)
    {
        this.laricas = laricas;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View v = inflater.from(parent.getContext()).inflate(R.layout.row_result, parent, false);
        return new ViewHolder(v);
    }

    public void setRecyclerVewOnCLickListenerHack (RecyclerVewOnCLickListenerHack rvcl)
    {
        recyclerVewOnCLickListenerHack = rvcl;
    }

    @Override
    public void onBindViewHolder (ViewHolder viewHolder, int position)
    {
        Larica larica = laricas.get(position);

        Uri uri = Uri.parse(larica.getLogo());
        viewHolder.image.setImageURI(uri);

        viewHolder.title.setText(larica.getNome());

        if (larica.isOpen())
        {
            viewHolder.statusIcon.setImageResource(R.drawable.ic_open2);
            viewHolder.statusText.setText(" Aberto");
        }
        else
        {
            viewHolder.statusIcon.setImageResource(R.drawable.ic_closed2);
            viewHolder.statusText.setText(" Fechado");
        }

        if (larica.getDistance() > 1)
        {
            viewHolder.distance.setText(" " + larica.getDistance() + " Km");
        }
        else
        {
            viewHolder.distance.setText(" " + (double) Math.round((larica.getDistance() * 100) * 100000) / 100000 + " m");
        }


        viewHolder.like.setText(" " + larica.getLike() + " %");
    }

    @Override
    public int getItemCount ()
    {
        return laricas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public SimpleDraweeView image;
        public TextView title;
        public ImageView statusIcon;
        public TextView statusText;
        public TextView like;
        public TextView distance;

        public ViewHolder (View itemView)
        {
            super(itemView);

            image = (SimpleDraweeView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            statusIcon = (ImageView) itemView.findViewById(R.id.statusIcon);
            statusText = (TextView) itemView.findViewById(R.id.statusText);
            like = (TextView) itemView.findViewById(R.id.resultLike);
            distance = (TextView) itemView.findViewById(R.id.resultDistance);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick (View v)
        {
            if (recyclerVewOnCLickListenerHack != null)
            {
                recyclerVewOnCLickListenerHack.onClickListener(v, getPosition());
            }
        }
    }

}

