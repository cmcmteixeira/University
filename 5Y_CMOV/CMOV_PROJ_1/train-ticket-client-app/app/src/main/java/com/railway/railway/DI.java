package com.railway.railway;

/**
 * Created by cteixeira on 21-10-2015.
 * com.railway.railway
 */
public class DI {
    static public ApplicationComponent component;
    static public ApplicationComponent get(){
        if(DI.component == null){
            DI.component = DaggerApplicationComponent.create();
        }
        return DI.component;
    }
}
