package com.railway.railwayconductor.business.security;

import com.railway.railwayconductor.business.api.entity.Ticket;
import com.railway.railwayconductor.business.api.storage.Storage;
import com.railway.railwayconductor.business.security.provider.RailwayPublicKeyProvider;
import com.railway.railwayconductor.business.security.signature.RailwaySignatureProvider;
import com.railway.railwayconductor.business.security.signature.SignatureValidator;
import com.railway.railwayconductor.business.security.strategy.TicketHashStrategy;

/**
 * Created by cteixeira on 08-11-2015.
 */
public class TicketValidator {
    private final SignatureValidator signatureValidator;

    public TicketValidator(Ticket ticket, Storage storage) {
        signatureValidator = new SignatureValidator(
                new TicketHashStrategy(ticket),
                new RailwaySignatureProvider(ticket),
                new RailwayPublicKeyProvider(storage)
        );
    }

    public boolean isValid(){
        return signatureValidator.validate();
    }
}
