package com.railway.railwayconductor.business.api.request;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.business.api.API;
import com.railway.railwayconductor.business.api.entity.Inspector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leonel on 07/11/2015.
 */
public class AuthRequest implements APIRequest {
    public final RequestFuture<JSONObject> future ;
    private final API api;
    JsonObjectRequest request;
    JSONObject requestData;

    public AuthRequest(AuthRequestData data) throws JSONException {
        String url = "https://cmovtrainserver.herokuapp.com/inspector/login";
        future = RequestFuture.newFuture();
        requestData = new JSONObject()
                .put("email", data.email)
                .put("password", data.password);
        request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                requestData,
                future,
                future
        );
        api = DI.get().provideRequestAPI();
    }

    public Inspector getResponse() throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        api.request(this);
        Inspector inspector = new Inspector(future.get());
        DI.get().provideStorage().setToken(inspector.token);
        DI.get().provideStorage().setPublicKey(inspector.publicKey);
        DI.get().provideStorage().setUser(inspector);
        return inspector;
    }

    @Override
    public JsonObjectRequest getRequest() {
        return request;
    }
}
