package com.railway.railwayconductor;

import android.app.Application;
import android.content.Context;


public class RailwayConductorApplication extends Application{

    private static RailwayConductorApplication instance;

    public static Context getContext(){
        return RailwayConductorApplication.instance.getApplicationContext();
    }

    public RailwayConductorApplication(){
        super();
        instance = this;
    }
}
