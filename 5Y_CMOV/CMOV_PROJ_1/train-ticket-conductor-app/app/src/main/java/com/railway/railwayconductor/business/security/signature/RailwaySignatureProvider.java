package com.railway.railwayconductor.business.security.signature;

import com.railway.railwayconductor.business.api.entity.Ticket;

/**
 * Created by cteixeira on 10-11-2015.
 */
public class RailwaySignatureProvider implements SignatureProvider {

    private final Ticket ticket;

    public RailwaySignatureProvider(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String provideSignature() {
        return ticket.getSignature();
    }
}
