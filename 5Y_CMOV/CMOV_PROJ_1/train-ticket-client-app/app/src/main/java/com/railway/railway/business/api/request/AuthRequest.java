package com.railway.railway.business.api.request;

import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.railway.railway.DI;
import com.railway.railway.business.api.API;
import com.railway.railway.business.api.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class AuthRequest implements APIRequest {
    public final RequestFuture<JSONObject> future ;
    private final API api;
    JsonObjectRequest request;
    JSONObject requestData;

    public AuthRequest(AuthRequestData data) throws JSONException {
        String url = "https://cmovtrainserver.herokuapp.com/login";
        future = RequestFuture.newFuture();
        requestData = new JSONObject()
                .put("email", data.email)
                .put("password", data.password);
        request = new JsonObjectRequest(
                Method.POST,
                url,
                requestData,
                future,
                future
        );
        api = DI.get().provideRequestAPI();
    }

    public User getResponse() throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        api.request(this);
        User user = new User(future.get());
        DI.get().provideStorage().setUser(user);
        return user;
    }

    @Override
    public JsonObjectRequest getRequest() {
        return request;
    }
}
