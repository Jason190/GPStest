package com.example.jason.gpstest;

import cn.bmob.v3.BmobObject;

/**
 * Created by jason on 2016/11/9.
 */

public class gpsPoint extends BmobObject {
    public gpsPoint(String name){
        this.setTableName(name);
    }
    public Number lon;
    public Number lat;
    public String name;

    public String getName(){
        return name;
    }
    public Number getLon(){
        return lon;
    }
    public Number getLat(){
        return lat;
    }

    public void setName(String n){
        name=n;
    }
    public void setLon(Number n){
        lon=n;
    }
    public void setLat(Number n){
        lat=n;
    }
}
