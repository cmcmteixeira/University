package com.railway.railwayconductor.business.api.request;

/**
 * Created by Leonel on 07/11/2015.
 */
public class AuthRequestData {
    public String email;
    public String password;

    public AuthRequestData(String username, String password){
        this.email = username;
        this.password = password;
    }
}

