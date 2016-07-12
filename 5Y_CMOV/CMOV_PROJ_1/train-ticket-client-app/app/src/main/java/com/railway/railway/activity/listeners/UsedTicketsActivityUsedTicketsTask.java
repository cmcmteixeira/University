package com.railway.railway.activity.listeners;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.railway.railway.R;
import com.railway.railway.activity.UsedTicketsActivity;
import com.railway.railway.activity.UserActivity;
import com.railway.railway.business.api.request.PurchaseTicketRequest;
import com.railway.railway.business.api.request.UsedTicketsRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leonel on 29/10/2015.
 */
public class UsedTicketsActivityUsedTicketsTask extends AsyncTask<Void, Void, JSONObject> {
    private UsedTicketsRequest request;
    private UsedTicketsActivity activity;


    public UsedTicketsActivityUsedTicketsTask(UsedTicketsActivity activity){
        this.activity = activity;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        try {
            this.request = new UsedTicketsRequest();
            return this.request.getResponse();

        } catch (JSONException | InterruptedException | ExecutionException | TimeoutException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result){
        Toast toast;
        if(result == null){
            toast = Toast.makeText(this.activity, R.string.used_tickets_task_error, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            try {
                this.activity.fillUsedTickets(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
