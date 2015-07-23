package com.laricafood.owner.app.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.laricafood.owner.app.bean.Message;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo on 23/06/15.
 */
public class MessageRepository extends SQLiteOpenHelper
{

    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS MESSAGE";

    private static final String SCRIPT_DATABASE_CREATE = "create table MESSAGE (_id integer primary key autoincrement, TITLE varchar not null, CONTENT varchar not null, DATE date not null, IS_UNREAD int not null)";

    private static final String DATABASE_NAME = "LARICAFOOD";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "MESSAGE";

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private final SQLiteDatabase db;

    private static MessageRepository sInstance;

    public static synchronized MessageRepository getInstance (Context ctx)
    {
        if (sInstance == null)
        {
            sInstance = new MessageRepository(ctx);
        }
        return sInstance;
    }

    private MessageRepository (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public void saveAll (List<Message> messageList)
    {
        for (Message message : messageList)
        {
            ContentValues values = new ContentValues();

            values.put(Message.MessageDB.TITLE, message.getTitle());
            values.put(Message.MessageDB.CONTENT, message.getContent());
            values.put(Message.MessageDB.DATE, format.format(message.getDate()));
            values.put(Message.MessageDB.IS_UNREAD, message.isUnread() ? 1 : 0);

            db.insert(TABLE_NAME, null, values);
        }
    }

    public void save (Message message)
    {
        ContentValues values = new ContentValues();

        values.put(Message.MessageDB.TITLE, message.getTitle());
        values.put(Message.MessageDB.CONTENT, message.getContent());
        values.put(Message.MessageDB.DATE, format.format(message.getDate()));
        values.put(Message.MessageDB.IS_UNREAD, message.isUnread() ? 1 : 0);

        db.insert(TABLE_NAME, null, values);
        close();
    }

    public List<Message> getAll ()
    {
        Cursor c = getCursor();

        List<Message> messages = new ArrayList<>();

        if (c != null && c.moveToFirst())
        {
            int idxId = c.getColumnIndex(Message.MessageDB._ID);
            int idxTitle = c.getColumnIndex(Message.MessageDB.TITLE);
            int idxContent = c.getColumnIndex(Message.MessageDB.CONTENT);
            int idxDate = c.getColumnIndex(Message.MessageDB.DATE);
            int idxIsUnread = c.getColumnIndex(Message.MessageDB.IS_UNREAD);
            do
            {

                Message message = new Message();
                message.setId(c.getLong(idxId));
                message.setTitle(c.getString(idxTitle));
                message.setContent(c.getString(idxContent));
                message.setUnread(c.getInt(idxIsUnread) == 1);
                try
                {
                    message.setDate(format.parse(c.getString(idxDate)));

                } catch (ParseException e)
                {
                    message.setDate(null);
                    e.printStackTrace();
                }
                messages.add(message);

            } while (c.moveToNext());
            c.close();
        }
        return messages;

    }

    public void update (Message message)
    {
        ContentValues values = new ContentValues();

        values.put(Message.MessageDB.TITLE, message.getTitle());
        values.put(Message.MessageDB.CONTENT, message.getContent());
        values.put(Message.MessageDB.DATE, message.getDate().toString());
        values.put(Message.MessageDB.IS_UNREAD, message.isUnread());

        db.update(TABLE_NAME, values, "_id " + "=" + message.getId(), null);
        close();
    }

    public int delete (Message message)
    {
        int i = db.delete(TABLE_NAME, "_id " + "=" + message.getId(), null);
        close();
        return i;
    }

    private Cursor getCursor ()
    {
        try
        {
            return db.rawQuery("select * from " + TABLE_NAME, null);
        } catch (Exception e)
        {
            return null;
        }

    }

    public void close ()
    {
        if (db != null)
        {
            db.close();
        }
    }

    @Override
    public void onCreate (SQLiteDatabase db)
    {
        db.execSQL(SCRIPT_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SCRIPT_DATABASE_DELETE);
        onCreate(db);
    }
}
