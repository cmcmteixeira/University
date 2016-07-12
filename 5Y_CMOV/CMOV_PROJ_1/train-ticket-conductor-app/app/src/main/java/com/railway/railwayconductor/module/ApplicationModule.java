package com.railway.railwayconductor.module;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.railway.railwayconductor.RailwayConductorApplication;
import com.railway.railwayconductor.business.adapter.TicketGSONAdapter;
import com.railway.railwayconductor.business.api.API;
import com.railway.railwayconductor.business.api.RailwayAPI;
import com.railway.railwayconductor.business.api.entity.Ticket;
import com.railway.railwayconductor.business.api.storage.RailwayStorage;
import com.railway.railwayconductor.business.api.storage.Storage;

import java.net.CookieHandler;
import java.net.CookieManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides @Singleton
    Context provideAppContext() {
        return RailwayConductorApplication.getContext();
    }

    @Provides @Inject @Singleton
    RequestQueue provideRequestQueue(Context context) {
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        return Volley.newRequestQueue(context);
    }

    @Provides @Singleton
    Storage provideStorage(){
       return new RailwayStorage();
    }

    @Provides @Singleton @Inject
    API provideAPI(RequestQueue queue) {
        return new RailwayAPI(queue);
    }
    @Provides @Singleton
    Gson provideGSON(){
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Ticket.class, new TicketGSONAdapter());
        return gson.create();
    }


}
