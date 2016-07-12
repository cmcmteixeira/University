package com.railway.railway.business.api.request;


import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Leonel on 26/10/2015.
 */
public class PurchaseTicketRequestData {

    String arrival;
    String departure;
    Timestamp datetime;

    public PurchaseTicketRequestData(String arrival, String departure, Long departureTime) {
        this.arrival = arrival;
        this.departure = departure;
        this.datetime = new Timestamp(departureTime);

    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public Timestamp getDateTime() {
        return datetime;
    }

}
