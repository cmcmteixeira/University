package com.railway.railway.business.api;

import com.android.volley.RequestQueue;
import com.railway.railway.business.api.context.APIContext;
import com.railway.railway.business.api.context.RailwayContext;
import com.railway.railway.business.api.request.APIRequest;

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
