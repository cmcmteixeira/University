package com.railway.railwayconductor.business.api.request;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.business.api.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leonel on 08/11/2015.
 */
public class ValidateTicketsRequest implements APIRequest{
    public final RequestFuture<JSONObject> future ;
    private final API api;
    JsonObjectRequest request;


    public ValidateTicketsRequest(ArrayList<String> data) throws JSONException {
        String url = "https://cmovtrainserver.herokuapp.com/ticket/validate";

        JSONObject postData = new JSONObject()
                .put("token", DI.get().provideStorage().getToken())
                .put("tickets", new JSONArray(data));

        future = RequestFuture.newFuture();
        request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postData,
                future,
                future
        );
        api = DI.get().provideRequestAPI();
    }
    @Override
    public Request getRequest()  {
        return request;
    }
    public JSONObject getResponse() throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        api.request(this);
        return future.get();
    }
}
