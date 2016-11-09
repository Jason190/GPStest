package com.example.jason.gpstest;

import cn.bmob.v3.BmobObject;

/**
 * Created by jason on 2016/11/9.
 */

public class gpsPoint extends BmobObject {
    public Number lon;
    public Number lat;

    public Number getLon(){
        return lon;
    }
    public Number getLat(){
        return lat;
    }
    public void setLon(Number n){
        lon=n;
    }
    public void setLat(Number n){
        lat=n;
    }
}
