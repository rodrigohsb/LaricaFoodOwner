package com.laricafood.owner.app.bean;

import java.io.Serializable;

/**
 * Created by Rodrigo on 01/03/15.
 */
public class Larica implements Serializable
{
    private int id;

    private String nome;

    private String endereco;

    private String number;

    private String bairro;

    private String uf;

    private double distance;

    private boolean isOpen;

    private int like;

    private User user;

    private String logo;

    private String photo1;

    private String photo2;

    private String photo3;

    private String photo4;

    /* ATIVO(0), PENDENTE(1), INATIVO(2) */
    private int status;

    public Larica (int id, String nome, String endereco, String number, String bairro, String uf, double distance, boolean isOpen, int like, User user, String logo, String photo1, String photo2, String photo3, String photo4, int status)
    {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.number = number;
        this.bairro = bairro;
        this.uf = uf;
        this.distance = distance;
        this.isOpen = isOpen;
        this.like = like;
        this.user = user;
        this.logo = logo;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.status = status;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
    }

    public String getEndereco ()
    {
        return endereco;
    }

    public void setEndereco (String endereco)
    {
        this.endereco = endereco;
    }

    public String getBairro ()
    {
        return bairro;
    }

    public void setBairro (String bairro)
    {
        this.bairro = bairro;
    }

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    public boolean isOpen ()
    {
        return isOpen;
    }

    public void setOpen (boolean isOpen)
    {
        this.isOpen = isOpen;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public double getDistance ()
    {
        return distance;
    }

    public void setDistance (double distance)
    {
        this.distance = distance;
    }

    public int getLike ()
    {
        return like;
    }

    public void setLike (int like)
    {
        this.like = like;
    }

    public String getUf ()
    {
        return uf;
    }

    public void setUf (String uf)
    {
        this.uf = uf;
    }

    public int getStatus ()
    {
        return status;
    }

    public void setStatus (int status)
    {
        this.status = status;
    }

    public String getLogo ()
    {
        return logo;
    }

    public void setLogo (String logo)
    {
        this.logo = logo;
    }

    public String getPhoto1 ()
    {
        return photo1;
    }

    public void setPhoto1 (String photo1)
    {
        this.photo1 = photo1;
    }

    public String getPhoto2 ()
    {
        return photo2;
    }

    public void setPhoto2 (String photo2)
    {
        this.photo2 = photo2;
    }

    public String getPhoto3 ()
    {
        return photo3;
    }

    public void setPhoto3 (String photo3)
    {
        this.photo3 = photo3;
    }

    public String getPhoto4 ()
    {
        return photo4;
    }

    public void setPhoto4 (String photo4)
    {
        this.photo4 = photo4;
    }

    @Override
    public String toString ()
    {
        return "Larica{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", number='" + number + '\'' +
                ", bairro='" + bairro + '\'' +
                ", uf='" + uf + '\'' +
                ", distance=" + distance +
                ", isOpen=" + isOpen +
                ", like=" + like +
                ", user=" + user +
                ", logo='" + logo + '\'' +
                ", photo1='" + photo1 + '\'' +
                ", photo2='" + photo2 + '\'' +
                ", photo3='" + photo3 + '\'' +
                ", photo4='" + photo4 + '\'' +
                ", status=" + status +
                '}';
    }
}
