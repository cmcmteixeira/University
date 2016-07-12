package com.railway.railway.business.api.request;

/**
 * Created by cteixeira on 22-10-2015.
 * com.railway.railway.business.api.request
 */
public class AuthRequestData {
    public String email;
    public String password;

    public AuthRequestData(String username, String password){
        this.email = username;
        this.password = password;
    }
}
