package com.railway.railwayconductor.business.api.storage;


import com.railway.railwayconductor.business.api.entity.Inspector;
import com.railway.railwayconductor.business.api.entity.Railway;
import com.railway.railwayconductor.business.api.entity.Ticket;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cteixeira on 21-10-2015.
 * com.railway.railway.business.api.storage
 */
public interface Storage {
    public class AlreadyExists extends Exception{

    }
    String getToken();
    void setToken(String token);

    Railway getSchedule();
    void setSchedule(Railway schedule);

    Inspector getInspector();
    void setUser(Inspector ins);

    void cacheResult(String call, JSONObject response);
    JSONObject getCachedResult(String call);

    String getPublicKey();
    void setPublicKey(String publicKey);

    // Quando termina uma viagem, o Inspector envia para o servidor os ID's dos bilhetes validados
    void addValidatedTicketID(String id) throws AlreadyExists;
    ArrayList<String> getValidatedIDs();
    void resetValidatedTickets();

    List<Ticket> getTickets();
    Storage setTickets(List<Ticket> val);
}
