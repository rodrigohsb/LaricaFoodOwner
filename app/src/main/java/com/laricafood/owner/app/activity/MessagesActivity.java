package com.laricafood.owner.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.appevents.AppEventsLogger;
import com.laricafood.owner.app.R;
import com.laricafood.owner.app.adapter.MessagesListAdapter;
import com.laricafood.owner.app.bean.Message;
import com.laricafood.owner.app.persistence.MessageRepository;
import com.laricafood.owner.app.util.AlertUtils;

import java.util.List;

/**
 * Created by Rodrigo on 23/06/15.
 */
public class MessagesActivity extends AppCompatActivity
{

    private Context ctx;

    private ListView lv;

    private TextView messageNoMessages;

    private MessagesListAdapter adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_messages);

        ctx = this;

        lv = (ListView) findViewById(R.id.messageList);
        messageNoMessages = (TextView) findViewById(R.id.messageNoMessages);

        final List<Message> messages = MessageRepository.getInstance(ctx).getAll();

        if (messages == null || messages.isEmpty())
        {
            messageNoMessages.setVisibility(View.VISIBLE);
        }
        else
        {
            lv.setVisibility(View.VISIBLE);

            adapter = new MessagesListAdapter(ctx, messages);

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick (AdapterView<?> parent, View view, int position, long id)
                {
                    Message message = messages.get(position);

                    Intent it = new Intent(ctx, MessageDetailsActivity.class);
                    it.putExtra("message", message);
                    startActivity(it);
                }
            });
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
            {
                @Override
                public boolean onItemLongClick (AdapterView<?> parent, View view, final int position, long id)
                {
                    DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener()
                    {
                        public void onClick (DialogInterface dialog, int id)
                        {
                            Message message = messages.get(position);

                            final String msg;
                            int i = MessageRepository.getInstance(ctx).delete(message);
                            if (i == 1)
                            {
                                adapter.removeItemAt(position);
                                adapter.notifyDataSetChanged();
                                if (adapter.getCount() == 0)
                                {
                                    lv.setVisibility(View.GONE);
                                    messageNoMessages.setVisibility(View.VISIBLE);
                                }
                                msg = "Mensagem deletada.";
                            }
                            else
                            {
                                msg = "Mensagem não deletada.";
                            }
                            ((Activity) ctx).runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run ()
                                {
                                    Toast.makeText(ctx.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                }
                            });

                            dialog.dismiss();
                        }
                    };
                    DialogInterface.OnClickListener setNegativeButton = new DialogInterface.OnClickListener()
                    {
                        public void onClick (DialogInterface dialog, int id)
                        {
                            dialog.dismiss();
                        }
                    };

                    new AlertUtils(ctx).getAlertDialog("Deseja deletar?", null, true, positiveButton, "Sim", setNegativeButton, "Não").show();

                    return true;
                }
            });
        }

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
