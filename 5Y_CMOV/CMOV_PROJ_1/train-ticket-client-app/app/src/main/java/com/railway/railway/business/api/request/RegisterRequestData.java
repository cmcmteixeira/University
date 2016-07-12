package com.railway.railway.business.api.request;

/**
 * Created by cteixeira on 22-10-2015.
 * com.railway.railway.business.api.request
 */
public class RegisterRequestData {
    public final String name;
    public final String email;
    public final String password;
    public final String cardType;
    public final String cardNumber;
    public final String validity;
    public RegisterRequestData(
            String name,
            String username,
            String password,
            String cardType,
            String cardNumber,
            String validity
    ) {
        this.name = name;
        this.email = username;
        this.password = password;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.validity = validity;
    }
}

