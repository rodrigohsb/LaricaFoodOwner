package com.laricafood.owner.app.bean;

import java.io.Serializable;

/**
 * Created by Rodrigo on 17/05/15.
 */
public class Category implements Serializable
{

    private String name;

    private int resourceId;

    public Category (String name, int resourceName)
    {
        this.name = name;
        this.resourceId = resourceName;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public int getResourceId ()
    {
        return resourceId;
    }

    public void setResourceId (int resourceId)
    {
        this.resourceId = resourceId;
    }
}
