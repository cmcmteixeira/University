package com.railway.railwayconductor.business.security.strategy;

import com.railway.railwayconductor.business.api.entity.Ticket;

import java.text.ParseException;

/**
 * Created by cteixeira on 10-11-2015.
 */
public class TicketHashStrategy implements HashStrategy{
    private final Ticket ticket;

    public TicketHashStrategy(Ticket ticket) {

        this.ticket = ticket;
    }

    @Override
    public String getStringToHash() {
        try {
            return ticket.getId() + ticket.getDeparture() + ticket.getArrival() + ticket.getDepartureTimeTimestamp();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
