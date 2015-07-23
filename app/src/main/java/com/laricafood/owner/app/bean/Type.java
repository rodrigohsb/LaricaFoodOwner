package com.laricafood.owner.app.bean;

/**
 * Created by Rodrigo on 26/06/15.
 */
public enum Type
{
    COMERCIANTE(0), CLIENTE(1);

    public int value;

    Type (int value)
    {
        this.value = value;
    }

    public int getValue ()
    {
        return value;
    }
}
