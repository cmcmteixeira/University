package com.railway.railway.business.api.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Leonel on 23/10/2015.
 */
public class Ticket implements Serializable {

    private int id;
    private String departure;
    private String arrival;
    private boolean validated;
    private float price;
    private Timestamp departureDateTime;
    private String signature;



    public Ticket(JSONObject t) throws JSONException {
        this.id = (Integer) t.get("id");
        this.departure = t.get("departure").toString();
        this.arrival = t.get("arrival").toString();
        this.validated = (boolean) t.get("validated");
        this.price = Float.valueOf(t.get("price").toString());
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            this.departureDateTime = new Timestamp(formatter.parse(t.get("departureTime").toString()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.signature = t.get("signature").toString();

    }
    public int getId() {
        return id;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public Boolean getValidated() {
        return validated;
    }

    public Float getPrice() {
        return price;
    }

    public Timestamp getDepartureDateTime() {
        return departureDateTime;
    }

    public String getDepartureDateTimeFormatted(){
        return new SimpleDateFormat("HH:mm dd/MM/yyyy").format(this.departureDateTime);
    }


    public String getSignature() {
        return signature;
    }


}
