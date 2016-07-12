package com.railway.railwayconductor.business.api.request;

/**
 * Created by Leonel on 07/11/2015.
 */
public class TicketListRequestData {

    public String arrival;
    public String departure;
    public String timestamp;

    public TicketListRequestData(String departure, String arrival, String timestamp) {
        this.arrival = arrival;
        this.departure = departure;
        this.timestamp = timestamp;
    }

}
