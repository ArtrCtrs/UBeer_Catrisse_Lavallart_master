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
    public Bar()
    {
    }


    public String getName()
    {
        return this.name;
    }
    public void setName(String nname){
        this.name = nname;
    }
    public String getAddress()
    {
        return this.address;
    }
    public void setAddress(String naddress){
        this.address = naddress;
    }
    public String getIsOpen()
    {
        return this.isOpen;
    }
    public void setIsopen(String nIsopen){
        this.isOpen = nIsopen;
    }
    public String getRank()
    {
        return this.rank;
    }
    public void setRank(String nrank){
        this.rank = nrank;
    }
    public String getUrl()
    {
        return this.imageUrl;
    }
    public void setUrl(String nurl){
        this.imageUrl = nurl;
    }

    @Override
    public String toString()
    {
        return this.name;
    }


}
