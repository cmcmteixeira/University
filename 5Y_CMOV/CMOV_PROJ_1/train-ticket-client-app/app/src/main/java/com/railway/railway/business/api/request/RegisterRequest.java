package com.railway.railway.business.api.request;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.railway.railway.DI;
import com.railway.railway.business.api.API;
import com.railway.railway.business.api.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by cteixeira on 22-10-2015.
 * com.railway.railway.business.api.request
 */
public class RegisterRequest implements APIRequest {


    public final RequestFuture<JSONObject> future ;
    private final JsonObjectRequest request;
    private final API api;

    public RegisterRequest(RegisterRequestData data) throws JSONException {

        JSONObject data1 = new JSONObject()
                .put("name", data.name)
                .put("email", data.email)
                .put("password", data.password)
                .put("cardType", data.cardType)
                .put("cardNumber", data.cardNumber)
                .put("validity", data.validity);

        String url = "https://cmovtrainserver.herokuapp.com/user";
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
    public Request getRequest() {
        return this.request;
    }

    public JSONObject getResponse() throws ExecutionException, InterruptedException, JSONException {
        api.request(this);
        JSONObject response = future.get();
        DI.get().provideStorage().setUser(new User(response));
        return response;
    }

}
