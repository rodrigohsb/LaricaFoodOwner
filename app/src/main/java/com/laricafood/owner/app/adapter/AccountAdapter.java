package com.laricafood.owner.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laricafood.owner.app.R;
import com.laricafood.owner.app.bean.Larica;

import java.util.List;

/**
 * Created by Rodrigo on 22/06/15.
 */
public class AccountAdapter extends BaseAdapter
{

    private List<Larica> laricas;
    private static LayoutInflater inflater = null;

    public AccountAdapter (Context ctx, List<Larica> laricas)
    {
        this.laricas = laricas;
        Context ctx1 = ctx;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount ()
    {
        return laricas.size();
    }

    @Override
    public Object getItem (int position)
    {
        return laricas.get(position);
    }

    @Override
    public long getItemId (int position)
    {
        return position;
    }

    @Override
    public View getView (final int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;

        ViewHolder holder;

        if (convertView == null)
        {
            holder = new ViewHolder();

            vi = inflater.inflate(R.layout.row_account, null);

            holder.title = (TextView) vi.findViewById(R.id.accountTitle);
            holder.isOpenStatusIcon = (ImageView) vi.findViewById(R.id.accountIsOpenIcon);
            holder.isOpenStatusText = (TextView) vi.findViewById(R.id.accountIsOpenText);
            holder.statusIcon = (ImageView) vi.findViewById(R.id.accountStatusIcon);
            holder.statusText = (TextView) vi.findViewById(R.id.accountStatusText);

            vi.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        final Larica larica = laricas.get(position);

        holder.title.setText(larica.getNome());

        if (larica.getStatus() == 0)
        {
            holder.statusIcon.setImageResource(R.drawable.ic_open);
            holder.statusText.setText("Ativo");

            if (larica.isOpen())
            {
                holder.isOpenStatusIcon.setImageResource(R.drawable.ic_open2);
                holder.isOpenStatusText.setText("Aberto");
            }
            else
            {
                holder.isOpenStatusIcon.setImageResource(R.drawable.ic_closed2);
                holder.isOpenStatusText.setText("Fechado");
            }
        }
        else
        {
            holder.statusIcon.setImageResource(R.drawable.ic_closed);
            holder.isOpenStatusIcon.setImageResource(R.drawable.ic_transparent);
            holder.isOpenStatusText.setText("------");
            holder.statusText.setText(larica.getStatus() == 1 ? "Em aprovação" : "Inativo");

        }

        return vi;
    }

    private static class ViewHolder
    {
        TextView title;
        ImageView statusIcon;
        TextView statusText;
        ImageView isOpenStatusIcon;
        TextView isOpenStatusText;
    }
}
