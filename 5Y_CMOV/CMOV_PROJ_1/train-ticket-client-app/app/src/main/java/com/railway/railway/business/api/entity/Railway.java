package com.railway.railway.business.api.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Leonel on 24/10/2015.
 */
public class Railway {

    private ArrayList<String> stations;
    private ArrayList<Connection> connections;

    public Railway(JSONObject railwayInfo) throws JSONException {
        // Stations Array
        this.stations = new ArrayList<>();
        JSONArray stationsInfo = railwayInfo.getJSONArray("stations");

        if (stationsInfo != null) {
            for (int i=0;i<stationsInfo.length();i++){
                this.stations.add(stationsInfo.get(i).toString());
            }
        }

        // Connections
        this.connections = new ArrayList<>();
        JSONObject connectionsInfo = railwayInfo.getJSONObject("connections");

        if (connectionsInfo != null) {
            for (int i=0;i<connectionsInfo.names().length();i++){
                JSONObject currConn = connectionsInfo.getJSONObject(connectionsInfo.names().get(i).toString());
                Connection currentConn = new Connection(currConn);
                this.connections.add(currentConn);
            }
        }
    }

    public ArrayList<String> getStations() {
        return stations;
    }

    public ArrayList<String> getTimetable(String from, String to){
        for(int i=0; i < connections.size(); i++){
            if(connections.get(i).getDepartureStation().equals(from) && connections.get(i).getArrivalStation().equals(to)){
                return connections.get(i).getSchedule();
            }
        }
        return new ArrayList<>();
    }

    public double getPrice(String from, String to){
        for(int i=0; i < connections.size(); i++){
            if(connections.get(i).getDepartureStation().equals(from) && connections.get(i).getArrivalStation().equals(to)){
                return connections.get(i).getPrice();
            }
        }
        return -1;
    }

}
