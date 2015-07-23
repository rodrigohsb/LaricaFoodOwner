package com.laricafood.owner.app.bean;

import android.provider.BaseColumns;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Rodrigo on 20/06/15.
 */
public class User implements Serializable
{
    public static String[] columns = new String[]{UserDB._ID , User.UserDB.FACEBOOK_ID , UserDB.FIRST_NAME , UserDB.MIDDLE_NAME , UserDB.LAST_NAME , UserDB.PICTURE , UserDB.TYPE , UserDB.CREATION_DATE , UserDB.UPDATE_DATE , UserDB.LAST_PAYMENT_DATE , UserDB.NEXT_PAYMENT_DATE};

    private Integer id;

    private String facebookId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String pictureUrl;

    private Type type;

    private Date lastPaymentDate;

    private Date nextPaymentDate;

    private Date creationDate;

    private Date updateDate;

    /* ATIVO(0), PENDENTE(1) */
    private int status;

    private List<Larica> laricas;

    public User ()
    {
    }

    public User (String facebookId, String firstName, String middleName, String lastName, String pictureUrl, Type type, Date creationDate, Date updateDate, int status, List<Larica> laricas)
    {
        this.facebookId = facebookId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.type = type;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.status = status;
        this.laricas = laricas;
    }

    public Integer getId ()
    {
        return id;
    }

    public void setId (Integer id)
    {
        this.id = id;
    }

    public String getFacebookId ()
    {
        return facebookId;
    }

    public void setFacebookId (String facebookId)
    {
        this.facebookId = facebookId;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public String getMiddleName ()
    {
        return middleName;
    }

    public void setMiddleName (String middleName)
    {
        this.middleName = middleName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getPictureUrl ()
    {
        return pictureUrl;
    }

    public void setPictureUrl (String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    public Type getType ()
    {
        return type;
    }

    public void setType (Type type)
    {
        this.type = type;
    }

    public Date getLastPaymentDate ()
    {
        return lastPaymentDate;
    }

    public void setLastPaymentDate (Date lastPaymentDate)
    {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Date getNextPaymentDate ()
    {
        return nextPaymentDate;
    }

    public void setNextPaymentDate (Date nextPaymentDate)
    {
        this.nextPaymentDate = nextPaymentDate;
    }

    public Date getCreationDate ()
    {
        return creationDate;
    }

    public void setCreationDate (Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate ()
    {
        return updateDate;
    }

    public void setUpdateDate (Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public int getStatus ()
    {
        return status;
    }

    public void setStatus (int status)
    {
        this.status = status;
    }

    public List<Larica> getLaricas ()
    {
        return laricas;
    }

    public void setLaricas (List<Larica> laricas)
    {
        this.laricas = laricas;
    }

    @Override
    public String toString ()
    {
        return "User{" +
                "id=" + id +
                ", facebookId='" + facebookId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", type=" + type +
                ", lastPaymentDate=" + lastPaymentDate +
                ", nextPaymentDate=" + nextPaymentDate +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", status=" + status +
                ", laricas=" + laricas +
                '}';
    }

    public static final class UserDB implements BaseColumns
    {
        public static final String DEFAULT_SORT_ORDER = "id ACS";

        public static final String FACEBOOK_ID = "FACEBOOK_ID";

        public static final String FIRST_NAME = "FIRST_NAME";

        public static final String MIDDLE_NAME = "MIDDLE_NAME";

        public static final String LAST_NAME = "LAST_NAME";

        public static final String PICTURE = "PICTURE";

        public static final String TYPE = "TYPE";

        public static final String CREATION_DATE = "CREATION_DATE";

        public static final String UPDATE_DATE = "UPDATE_DATE";

        public static final String LAST_PAYMENT_DATE = "LAST_PAYMENT_DATE";

        public static final String NEXT_PAYMENT_DATE = "NEXT_PAYMENT_DATE";

        public static final String STATUS = "STATUS";

    }
}
