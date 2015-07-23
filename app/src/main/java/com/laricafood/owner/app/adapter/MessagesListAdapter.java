package com.laricafood.owner.app.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laricafood.owner.app.R;
import com.laricafood.owner.app.bean.Message;

import java.util.List;

/**
 * Created by Rodrigo on 22/06/15.
 */
public class MessagesListAdapter extends BaseAdapter
{

    private Context ctx;
    private List<Message> messages;
    private static LayoutInflater inflater = null;

    public MessagesListAdapter (Context ctx, List<Message> messages)
    {
        this.ctx = ctx;
        this.messages = messages;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount ()
    {
        return messages.size();
    }

    @Override
    public Object getItem (int position)
    {
        return position;
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

            vi = inflater.inflate(R.layout.row_dialog_messages, null);

            holder.messageImage = (ImageView) vi.findViewById(R.id.messageIcon);
            holder.messageTitle = (TextView) vi.findViewById(R.id.messageTitle);
            holder.messageContent = (TextView) vi.findViewById(R.id.messageContent);

            vi.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Message message = messages.get(position);

        holder.messageTitle.setText(message.getTitle());

        if (message.isUnread())
        {
            holder.messageImage.setImageResource(R.drawable.ic_unread);
            holder.messageTitle.setTypeface(null, Typeface.BOLD);
            holder.messageContent.setTypeface(null, Typeface.BOLD);
        }
        else
        {
            holder.messageImage.setImageResource(R.drawable.ic_read);
            holder.messageTitle.setTypeface(null, Typeface.NORMAL);
            holder.messageContent.setTypeface(null, Typeface.NORMAL);
        }

        holder.messageContent.setText(message.getContent());

        return vi;
    }

    public void removeItemAt (int position)
    {
        messages.remove(position);
    }

    private static class ViewHolder
    {
        ImageView messageImage;
        TextView messageTitle;
        TextView messageContent;
    }
}
