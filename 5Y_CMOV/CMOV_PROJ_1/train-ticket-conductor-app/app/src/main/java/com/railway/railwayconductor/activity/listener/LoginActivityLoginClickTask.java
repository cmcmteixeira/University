package com.railway.railwayconductor.activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.railway.railwayconductor.activity.LoginActivity;
import com.railway.railwayconductor.activity.SelectStationsActivity;
import com.railway.railwayconductor.business.api.entity.Inspector;
import com.railway.railwayconductor.business.api.request.AuthRequest;
import com.railway.railwayconductor.business.api.request.AuthRequestData;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leonel on 07/11/2015.
 */
public class LoginActivityLoginClickTask extends AsyncTask<String,Void,Inspector> {
    private Activity activity;
    private AuthRequestData data;
    boolean already = false;

    LoginActivityLoginClickTask(Activity view,AuthRequestData data){
        this.activity = view;
        this.data = data;
    }

    @Override
    protected Inspector doInBackground(String... params) {

        try {
            AuthRequest request = new AuthRequest(this.data);
            return request.getResponse();
        } catch (JSONException | TimeoutException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Inspector response) {
        Toast toast;
        ((LoginActivity) activity).enableButtons();
        if (response != null){
            toast = Toast.makeText(activity,"Logging in..." ,Toast.LENGTH_LONG);
            Intent intent = new Intent(activity, SelectStationsActivity.class);
            activity.startActivity(intent);
            activity.finish();
        } else {
            toast = Toast.makeText(activity,"Please verify your credentials!",Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}