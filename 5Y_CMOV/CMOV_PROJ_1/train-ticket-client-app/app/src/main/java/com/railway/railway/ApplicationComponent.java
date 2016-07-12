package com.railway.railway;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.railway.railway.business.api.API;
import com.railway.railway.business.api.storage.Storage;
import com.railway.railway.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    Context provideAppContext();
    RequestQueue provideRequestQueue();
    API provideRequestAPI();
    Storage provideStorage();
    Gson provideGSON();
}
