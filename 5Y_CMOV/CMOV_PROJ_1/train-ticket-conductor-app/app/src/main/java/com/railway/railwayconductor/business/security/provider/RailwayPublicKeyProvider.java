package com.railway.railwayconductor.business.security.provider;

import com.railway.railwayconductor.business.api.storage.Storage;

/**
 * Created by cteixeira on 10-11-2015.
 */
public class RailwayPublicKeyProvider implements PublicKeyProvider {

    private final Storage storage;

    public RailwayPublicKeyProvider(Storage storage){
        this.storage = storage;
    }

    @Override
    public String provideKey() {
        String pkey = storage.getPublicKey();
        return pkey.substring(pkey.indexOf('\n')+1,pkey.lastIndexOf('\n'));
    }
}
