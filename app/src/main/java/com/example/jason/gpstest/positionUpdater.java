package com.example.jason.gpstest;

import cn.bmob.v3.BmobObject;

/**
 * Created by jason on 2016/11/14.
 */

public class positionUpdater extends BmobObject{
    public String name;
    public String pwd;
    public String getPwd(){return pwd;}
    public String getName(){return name;}
    public void setName(String s){this.name=s;}
    public void setPwd(String s){this.pwd=s;}
}
