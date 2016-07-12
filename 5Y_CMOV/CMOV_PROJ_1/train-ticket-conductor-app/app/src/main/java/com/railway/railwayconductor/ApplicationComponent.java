package com.railway.railwayconductor;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.railway.railwayconductor.business.api.API;
import com.railway.railwayconductor.business.api.storage.Storage;
import com.railway.railwayconductor.module.ApplicationModule;

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
