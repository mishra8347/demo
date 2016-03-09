package com.mishra.mock.utilities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Shailendra on 09-03-2016.
 */
public class NetworkResponseOuputVO implements Parcelable
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

    protected NetworkResponseOuputVO(Parcel in)
    {
        id = in.readString();
        name = in.readString();
        car = in.readString();
        train = in.readString();
        lat = in.readString();
        locationList = in.createTypedArrayList(NetworkResponseOuputVO.CREATOR);
        vehicalList = in.createTypedArrayList(NetworkResponseOuputVO.CREATOR);
        lng = in.readString();
    }

    public static final Creator<NetworkResponseOuputVO> CREATOR = new Creator<NetworkResponseOuputVO>()
    {
        @Override
        public NetworkResponseOuputVO createFromParcel(Parcel in)
        {
            return new NetworkResponseOuputVO(in);
        }

        @Override
        public NetworkResponseOuputVO[] newArray(int size)
        {
            return new NetworkResponseOuputVO[size];
        }
    };

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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(car);
        dest.writeString(train);
        dest.writeString(lat);
        dest.writeTypedList(locationList);
        dest.writeTypedList(vehicalList);
        dest.writeString(lng);
    }
}
