package com.railway.railwayconductor.business.api.storage;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.Logger;
import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.business.api.entity.Inspector;
import com.railway.railwayconductor.business.api.entity.Railway;
import com.railway.railwayconductor.business.api.entity.Ticket;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cteixeira on 21-10-2015.
 * com.railway.railway.business.api.storage
 */
public class RailwayStorage implements Storage {

    private HashMap<String,String> storage;
    private HashMap<String,JSONObject> responseStorage;
    private List<Ticket> tickets;
    private Railway schedule;
    private Inspector inspector;
    private String publicKey;
    private ArrayList<String> validatedIDs;


    public RailwayStorage() {

        final Context context = DI.get().provideAppContext();
        this.storage = new HashMap<>();
        this.responseStorage = new HashMap<>();
        this.tickets = new ArrayList<>();
        this.validatedIDs = new ArrayList<>();
        try {
            Log.println(Logger.LogLevel.ERROR,"DEBUG","Loading Inspector");
            FileInputStream file = context.openFileInput("inspector");
            ObjectInputStream oin = new ObjectInputStream(file);
            inspector = (Inspector)oin.readObject();
            Log.println(Logger.LogLevel.ERROR,"DEBUG","Loading Tickets");

            file = context.openFileInput("tickets");
            oin = new ObjectInputStream(file);
            validatedIDs = (ArrayList<String>)oin.readObject();
            Log.println(Logger.LogLevel.ERROR,"DEBUG","All Loaded");

        } catch (ClassNotFoundException | IOException e) {
            tickets = new ArrayList<>();
            validatedIDs = new ArrayList<>();
        }

    }

    private void save()  {
        try {
            final Context context = DI.get().provideAppContext();

            FileOutputStream file = context.openFileOutput("inspector", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(this.getInspector());
            oos.close();

            file = context.openFileOutput("tickets", Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(file);
            oos.writeObject(this.getValidatedIDs());
            oos.close();
        } catch (Exception e) {
            int r = 2;
        }

    }


    @Override
    public String getToken() {
        return storage.get("token");
    }

    @Override
    public void setToken(String token) {
        storage.put("token",token);
        save();
    }

    @Override
    public Railway getSchedule() {
        return this.schedule;
    }

    @Override
    public void setSchedule(Railway schedule) {
        this.schedule = schedule;
    }

    @Override
    public Inspector getInspector() {
        return this.inspector;
    }

    @Override
    public void setUser(Inspector ins) {
        this.inspector = ins;
        save();
    }


    @Override
    public void cacheResult(String call, JSONObject response){
        responseStorage.put(call, response);
    }

    @Override
    public JSONObject getCachedResult(String call) {
        return responseStorage.get(call);
    }

    @Override
    public List<Ticket> getTickets(){
        return new ArrayList<>(this.tickets);
    }

    @Override
    public Storage setTickets(List<Ticket> val){
        this.tickets = new ArrayList<>(val);
        return this;
    }

    @Override
    public String getPublicKey() {
        return this.inspector.publicKey;
    }

    @Override
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public void addValidatedTicketID(String id) throws AlreadyExists {
        for(String _id : this.validatedIDs){
            if (id.equals(_id))
                throw new AlreadyExists();
        }
        this.validatedIDs.add(id);
    }

    @Override
    public void resetValidatedTickets(){
        this.validatedIDs.clear();
    }

    @Override
    public ArrayList<String> getValidatedIDs(){
        return this.validatedIDs;
    }

}
