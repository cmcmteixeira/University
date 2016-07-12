package com.railway.railwayconductor.business.api.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Leonel on 07/11/2015.
 */
public class Inspector implements Serializable{

    public String id;
    public String name;
    public String email;
    public String token;
    public String publicKey;

    public Inspector(JSONObject response) throws JSONException {
        this.id = response.get("id").toString();
        this.name = (response.isNull("name")) ? "" : (response.get("name").toString());
        this.email = response.get("email").toString();
        this.token = response.get("token").toString();
        this.publicKey = response.get("publicKey").toString();
    }
}
