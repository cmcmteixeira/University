package com.railway.railwayconductor.business.api;

import com.android.volley.RequestQueue;
import com.railway.railwayconductor.business.api.context.APIContext;
import com.railway.railwayconductor.business.api.context.RailwayContext;
import com.railway.railwayconductor.business.api.request.APIRequest;

import javax.inject.Inject;


public class RailwayAPI implements API {

    APIContext apiContext;
    private RequestQueue client;

    @Inject
    public RailwayAPI(RequestQueue client){
        this.client = client;
        apiContext = new RailwayContext();
    }

    @Override
    public void request(APIRequest request) {
        client.add(request.getRequest());
    }
}
