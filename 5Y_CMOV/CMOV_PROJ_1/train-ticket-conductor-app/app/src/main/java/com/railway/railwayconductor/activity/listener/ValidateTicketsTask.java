package com.railway.railwayconductor.activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.activity.SelectStationsActivity;
import com.railway.railwayconductor.business.api.request.ValidateTicketsRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leonel on 08/11/2015.
 */
public class ValidateTicketsTask extends AsyncTask<Void, Void, String> {
    private Activity activity;
    private JSONObject response;

    public ValidateTicketsTask(Activity view){
        this.activity = view;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            ValidateTicketsRequest request = new ValidateTicketsRequest(DI.get().provideStorage().getValidatedIDs());
            response = request.getResponse();
            return response.get("description").toString();
        } catch (JSONException | TimeoutException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        Toast toast;
        if (response != null){
            toast = Toast.makeText(activity,response,Toast.LENGTH_LONG);
            Intent intent = new Intent(activity, SelectStationsActivity.class);
            DI.get().provideStorage().resetValidatedTickets();
            activity.startActivity(intent);
            activity.finish();
        } else {
            toast = Toast.makeText(activity,"An error occurred",Toast.LENGTH_LONG);
        }
        toast.show();

    }

}
