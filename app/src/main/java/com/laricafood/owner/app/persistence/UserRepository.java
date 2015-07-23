package com.laricafood.owner.app.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.laricafood.owner.app.bean.Type;
import com.laricafood.owner.app.bean.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Rodrigo on 05/07/15.
 */
public class UserRepository extends SQLiteOpenHelper
{

    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS USER";

    private static final String SCRIPT_DATABASE_CREATE = "create table USER (_id integer primary key autoincrement, FACEBOOK_ID varchar not null, FIRST_NAME varchar not null, MIDDLE_NAME varchar not null, LAST_NAME varchar not null, PICTURE varchar not null, TYPE integer not null, CREATION_DATE date not null, UPDATE_DATE date not null, LAST_PAYMENT_DATE date null, NEXT_PAYMENT_DATE date null)";

    private static final String DATABASE_NAME = "LARICAFOOD";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "USER";

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm");

    private final SQLiteDatabase db;

    private static UserRepository sInstance;

    public static synchronized UserRepository getInstance (Context ctx)
    {
        if (sInstance == null)
        {
            sInstance = new UserRepository(ctx);
        }
        return sInstance;
    }

    private UserRepository (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public void save (User user)
    {
        ContentValues values = getContentValues(user);

        db.insert(TABLE_NAME, null, values);
        close();
    }

    public User getUserByFacebookId (String facebookId)
    {
        Cursor c = getCursorByFacebookId(facebookId);

        if (c != null && c.moveToFirst())
        {
            User user = populateUser(c);
            close();
            return user;
        }
        return null;
    }

    public void update (User user)
    {
        ContentValues values = getContentValues(user);

        db.update(TABLE_NAME, values, User.UserDB.FACEBOOK_ID + "=" + user.getFacebookId(), null);
        close();
    }

    private ContentValues getContentValues (User user)
    {
        ContentValues values = new ContentValues();

        if (user.getFacebookId() != null)
        {
            values.put(User.UserDB.FACEBOOK_ID, user.getFacebookId());
        }
        if (user.getFirstName() != null)
        {
            values.put(User.UserDB.FIRST_NAME, user.getFirstName());
        }
        if (user.getMiddleName() != null)
        {
            values.put(User.UserDB.MIDDLE_NAME, user.getMiddleName());
        }
        if (user.getLastName() != null)
        {
            values.put(User.UserDB.LAST_NAME, user.getLastName());
        }
        if (user.getType() != null)
        {
            values.put(User.UserDB.TYPE, user.getType().getValue());
        }
        if (user.getCreationDate() != null)
        {
            values.put(User.UserDB.CREATION_DATE, user.getCreationDate().toString());
        }
        if (user.getUpdateDate() != null)
        {
            values.put(User.UserDB.UPDATE_DATE, user.getUpdateDate().toString());
        }
        if (user.getLastPaymentDate() != null)
        {
            values.put(User.UserDB.LAST_PAYMENT_DATE, user.getLastPaymentDate().toString());
        }
        if (user.getNextPaymentDate() != null)
        {
            values.put(User.UserDB.NEXT_PAYMENT_DATE, user.getNextPaymentDate().toString());
        }
        if (user.getPictureUrl() != null)
        {
            values.put(User.UserDB.PICTURE, user.getPictureUrl());
        }
        return values;
    }

    private User populateUser (Cursor c)
    {

        int idxId = c.getColumnIndex(User.UserDB._ID);
        int idxFacebookId = c.getColumnIndex(User.UserDB.FACEBOOK_ID);
        int idxFirstName = c.getColumnIndex(User.UserDB.FIRST_NAME);
        int idxMiddleName = c.getColumnIndex(User.UserDB.MIDDLE_NAME);
        int idxLastName = c.getColumnIndex(User.UserDB.LAST_NAME);
        int idxType = c.getColumnIndex(User.UserDB.TYPE);
        int idxCreationDate = c.getColumnIndex(User.UserDB.CREATION_DATE);
        int idxUpdateDate = c.getColumnIndex(User.UserDB.UPDATE_DATE);
        int idxLastPaymentDate = c.getColumnIndex(User.UserDB.LAST_PAYMENT_DATE);
        int idxNextPaymentDate = c.getColumnIndex(User.UserDB.NEXT_PAYMENT_DATE);
        int idxPicture = c.getColumnIndex(User.UserDB.PICTURE);

        User user = new User();

        user.setId(c.getInt(idxId));
        user.setFacebookId(c.getString(idxFacebookId));
        user.setFirstName(c.getString(idxFirstName));
        user.setMiddleName(c.getString(idxMiddleName));
        user.setLastName(c.getString(idxLastName));
        user.setPictureUrl(c.getString(idxPicture));

        int type = c.getInt(idxType);
        user.setType(type == 0 ? Type.COMERCIANTE : Type.CLIENTE);

        try
        {
            user.setCreationDate(format.parse(c.getString(idxCreationDate)));
            user.setUpdateDate(format.parse(c.getString(idxUpdateDate)));
            user.setLastPaymentDate(format.parse(c.getString(idxLastPaymentDate)));
            user.setNextPaymentDate(format.parse(c.getString(idxNextPaymentDate)));

        } catch (ParseException e)
        {
            user.setCreationDate(null);
            user.setUpdateDate(null);
        }

        return user;
    }

    public User getUser ()
    {

        User user = null;
        Cursor c = getCursorAll();

        if (c != null)
        {
            user = populateUser(c);

            close();
        }

        return user;
    }

    private Cursor getCursorAll ()
    {

        try
        {
            return db.rawQuery("select * from " + TABLE_NAME, null);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    private Cursor getCursorByFacebookId (String facebookId)
    {
        try
        {
            return db.query(true, TABLE_NAME, User.columns, User.UserDB.FACEBOOK_ID + "=" + facebookId, null, null, null, null, null);
        } catch (Exception e)
        {
            e.printStackTrace();
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

    public void delete (User User)
    {
        db.delete(TABLE_NAME, "_id " + "=" + User.getId(), null);
        close();
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
