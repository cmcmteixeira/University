package com.railway.railway.activity.listeners;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.railway.railway.R;
import com.railway.railway.activity.UserActivity;
import com.railway.railway.business.api.request.PurchaseTicketRequest;
import com.railway.railway.business.api.request.PurchaseTicketRequestData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leonel on 26/10/2015.
 */
public class PurchaseActivityPurchaseTicketTask extends AsyncTask<Void, Void, JSONObject> {

    private PurchaseTicketRequestData data;
    private PurchaseTicketRequest request;
    private Activity activity;

    public PurchaseActivityPurchaseTicketTask(Activity activity, PurchaseTicketRequestData data) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        try {
            this.request = new PurchaseTicketRequest(this.data);
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
            toast = Toast.makeText(this.activity, R.string.purchase_error, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast = Toast.makeText(this.activity, R.string.purchase_success, Toast.LENGTH_SHORT);
            Intent intent = new Intent(this.activity, UserActivity.class);
            intent.putExtra("forceCall", "true");
            this.activity.startActivity(intent);
            toast.show();
        }
    }
}
