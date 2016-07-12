package com.railway.railway.activity.listeners;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.railway.railway.DI;
import com.railway.railway.activity.PurchaseSelectStationsActivity;
import com.railway.railway.business.api.entity.Railway;
import com.railway.railway.business.api.request.RailwayInfoRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leonel on 24/10/2015.
 */
public class PurchaseActivityGetStationsTask extends AsyncTask<Void, Void, JSONObject> {

    private Activity activity;
    private RailwayInfoRequest request;
    private JSONObject result;

    public PurchaseActivityGetStationsTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {

        try {
            this.request = new RailwayInfoRequest();
            result = this.request.getResponse();
            return result;

        } catch (JSONException | InterruptedException | ExecutionException | TimeoutException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result){
        if(result == null){
            Toast toast;
            toast = Toast.makeText(this.activity,"There was an error retrieving stations",Toast.LENGTH_LONG);
            toast.show();
        } else {
            try {
                // Create Railway
                Railway r = new Railway(result);
                ((PurchaseSelectStationsActivity) activity).addDepartureOptions(r.getStations());
                ((PurchaseSelectStationsActivity) activity).addArrivalOptions(r.getStations());

                // Filling Railway Information singleton
                DI.get().provideStorage().setSchedule(r);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}