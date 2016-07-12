package com.railway.railway.business.api.request;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.railway.railway.DI;
import com.railway.railway.business.api.API;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leonel on 22/10/2015.
 */
public class MyTicketsRequest implements APIRequest{

    public final RequestFuture<JSONObject> future ;
    private final JsonObjectRequest request;
    private final API api;
    private boolean forceCall;

    public MyTicketsRequest(boolean forceCall) throws JSONException {
        JSONObject data1 = new JSONObject()
                .put("token", DI.get().provideStorage().getToken());

        String url = "https://cmovtrainserver.herokuapp.com/usr/tickets";
        this.future = RequestFuture.newFuture();

        this.request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                data1,
                future,
                future
        );
        api = DI.get().provideRequestAPI();
        this.forceCall = forceCall;
    }

    @Override
    public JsonObjectRequest getRequest() {
        return request;
    }

    public JSONObject getResponse() throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        if(this.forceCall || DI.get().provideStorage().getCachedResult("MyTicketsRequest") == null){
            api.request(this);
            JSONObject response = future.get();
            DI.get().provideStorage().cacheResult("MyTicketsRequest", response);
            return response;
        }
        else return DI.get().provideStorage().getCachedResult("MyTicketsRequest");
    }
}

