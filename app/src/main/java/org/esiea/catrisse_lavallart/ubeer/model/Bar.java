package org.esiea.catrisse_lavallart.ubeer.model;

import java.io.Serializable;

/**
 * Created by gustu on 23/03/2018.
 */



public class Bar implements Serializable {
    String name;
    String address;
    String isOpen;
    String rank;
    String imageUrl;

    public Bar(String name, String address, String isOpen, String rank, String imageUrl)
    {
        this.name=name;
        this.address=address;
        this.isOpen=isOpen;
        this.rank=rank;
        this.imageUrl=imageUrl;

    }


    public String getName()
    {
        return this.name;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getIsOpen()
    {
        return this.isOpen;
    }

    public String getRank()
    {
        return this.rank;
    }
    public String getUrl()
    {
        return this.imageUrl;
    }

    @Override
    public String toString()
    {
        return this.name;
    }


}
