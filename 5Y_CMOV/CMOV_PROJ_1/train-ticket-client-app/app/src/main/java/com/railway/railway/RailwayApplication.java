package com.railway.railway;

import android.app.Application;
import android.content.Context;


public class RailwayApplication extends Application{

    private static RailwayApplication instance;

    public static Context getContext(){
        return RailwayApplication.instance.getApplicationContext();
    }

    public RailwayApplication(){
        super();
        instance = this;
    }
}
