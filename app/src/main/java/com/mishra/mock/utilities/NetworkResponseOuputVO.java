package com.mishra.mock.utilities;

import java.util.ArrayList;

/**
 * Created by Shailendra on 09-03-2016.
 */
public class NetworkResponseOuputVO
{
    String id;
    String name;
    String car;
    String train;
    String lat;
    public ArrayList<NetworkResponseOuputVO> locationList, vehicalList;

    public NetworkResponseOuputVO()
    {
        locationList = new ArrayList<>();
        vehicalList = new ArrayList<>();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public String getCar()
    {
        return car;
    }

    public void setCar(String car)
    {
        this.car = car;
    }

    public String getTrain()
    {
        return train;
    }

    public void setTrain(String train)
    {
        this.train = train;
    }


    public String getLat()
    {
        return lat;
    }

    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public String getLng()
    {
        return lng;
    }

    public void setLng(String lng)
    {
        this.lng = lng;
    }

    String lng;
}
