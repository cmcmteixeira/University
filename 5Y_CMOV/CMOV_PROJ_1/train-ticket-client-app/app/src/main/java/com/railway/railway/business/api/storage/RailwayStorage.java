package com.railway.railway.business.api.storage;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.Logger;
import com.railway.railway.DI;
import com.railway.railway.business.api.entity.Railway;
import com.railway.railway.business.api.entity.Ticket;
import com.railway.railway.business.api.entity.User;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
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
    private ArrayList<Ticket> tickets;

    private Railway schedule;
    private User user;

    public RailwayStorage() {
        this.storage = new HashMap<>();
        this.responseStorage = new HashMap<>();
        try {
            final Context context = DI.get().provideAppContext();
            FileInputStream file = context.openFileInput("user");
            ObjectInputStream oin = new ObjectInputStream(file);
            user = (User)oin.readObject();

            file = context.openFileInput("tickets");
            oin = new ObjectInputStream(file);
            tickets = (ArrayList<Ticket>) oin.readObject();

        } catch (ClassNotFoundException | IOException e) {

        }
        if(tickets == null){
            tickets = new ArrayList<Ticket>();
        }
    }
    private void save()  {
        try {
            final Context context = DI.get().provideAppContext();
            FileOutputStream file = context.openFileOutput("user", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(this.getUser());
            oos.close();

            file = context.openFileOutput("tickets",Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(file);
            oos.writeObject(this.tickets);
        } catch (Exception e) {

        }
    }
    @Override
    public String getToken() {
        return user == null ? "" : user.token ;
    }

    @Override @Deprecated
    public void setToken(String token) {
        user.token = token;
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
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
        save();
    }

    @Override
    public void setTickets(List<Ticket> tickets){
        if (tickets == null)
            this.tickets = null;
        else
            this.tickets = new ArrayList<>(tickets);
        save();
    }

    @Override
    public List<Ticket> getTickets(){
        return this.tickets;
    }

    @Override
    public void cacheResult(String call, JSONObject response){
        responseStorage.put(call, response);
    }

    @Override
    public JSONObject getCachedResult(String call) {
        return responseStorage.get(call);
    }


}
