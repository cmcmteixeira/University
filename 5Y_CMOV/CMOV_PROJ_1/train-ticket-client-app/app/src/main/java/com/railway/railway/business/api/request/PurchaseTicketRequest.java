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
 * Created by Leonel on 26/10/2015.
 */
public class PurchaseTicketRequest implements APIRequest {
    public final RequestFuture<JSONObject> future ;
    private final JsonObjectRequest request;
    private final API api;

    public PurchaseTicketRequest(PurchaseTicketRequestData data) throws JSONException {
        JSONObject data1 = new JSONObject()
                .put("token", DI.get().provideStorage().getToken())
                .put("departure", data.getDeparture())
                .put("arrival", data.getArrival())
                .put("departureTime",data.getDateTime().getTime());

        String url = "https://cmovtrainserver.herokuapp.com/ticket/purchase";
        this.future = RequestFuture.newFuture();

        this.request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                data1,
                future,
                future
        );
        api = DI.get().provideRequestAPI();
    }

    @Override
    public JsonObjectRequest getRequest() {
        return request;
    }

    public JSONObject getResponse() throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        api.request(this);
        JSONObject response = future.get();
        return response;
    }

}
