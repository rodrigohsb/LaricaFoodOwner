package com.laricafood.owner.app.bean;

import android.provider.BaseColumns;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rodrigo on 22/06/15.
 */
public class Message implements Serializable
{

    private Long id;

    private String title;

    private String content;

    private Date date;

    private boolean isUnread;

    public Message ()
    {
    }

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public boolean isUnread ()
    {
        return isUnread;
    }

    public void setUnread (boolean isUnread)
    {
        this.isUnread = isUnread;
    }

    public Date getDate ()
    {
        return date;
    }

    public void setDate (Date date)
    {
        this.date = date;
    }

    @Override
    public String toString ()
    {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", isUnread=" + isUnread +
                '}';
    }

    public static final class MessageDB implements BaseColumns
    {
        public static final String DEFAULT_SORT_ORDER = "id ACS";

        public static final String TITLE = "TITLE";

        public static final String CONTENT = "CONTENT";

        public static final String DATE = "DATE";

        public static final String IS_UNREAD = "IS_UNREAD";

    }

}
