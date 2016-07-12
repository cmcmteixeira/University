package com.railway.railwayconductor.business.api.request;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.business.api.API;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cteixeira on 02-11-2015.
 *
 */
public class TicketListRequest implements APIRequest{
    public final RequestFuture<JSONObject> future ;
    private final API api;
    JsonObjectRequest request;


    public TicketListRequest(TicketListRequestData data) throws JSONException {
        String url = "https://cmovtrainserver.herokuapp.com/inspector/trip";

        JSONObject postData = new JSONObject()
                .put("departure", data.departure)
                .put("arrival", data.arrival)
                .put("departureTime", data.timestamp);

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
    public int getResponse() throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        api.request(this);
        JSONObject responseJson = future.get();

        //List<Ticket> tickets = new ArrayList<Ticket>();
        //JSONArray arr = responseJson.getJSONArray("tickets");
        //for(int i = 0 ; i < arr.length() ; i++){
            //    tickets.add(new Ticket(arr.getJSONObject(i)));
            //}
        //
        //DI.get().provideStorage().setTickets(tickets);

        return (int) responseJson.get("size");
    }
}
