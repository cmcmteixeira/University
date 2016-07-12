package com.railway.railway.activity.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.railway.railway.activity.LoginActivity;
import com.railway.railway.activity.UserActivity;
import com.railway.railway.business.api.entity.User;
import com.railway.railway.business.api.request.AuthRequest;
import com.railway.railway.business.api.request.AuthRequestData;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class LoginActivityLoginClickTask extends AsyncTask<String,Void,User> {
    private Activity activity;
    private AuthRequestData data;

    LoginActivityLoginClickTask(Activity view,AuthRequestData data){
        this.activity = view;
        this.data = data;
    }

    @Override
    protected User doInBackground(String... params) {

        try {
            AuthRequest request = new AuthRequest(this.data);
            return request.getResponse();
        } catch (JSONException | TimeoutException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(User response) {
        Toast toast;
        ((LoginActivity) activity).enableButtons();
        if (response != null){
            toast = Toast.makeText(activity,"Logging in...",Toast.LENGTH_LONG);
            Intent intent = new Intent(activity, UserActivity.class);
            intent.putExtra("forceCall", "true");
            activity.startActivity(intent);
            activity.finish();
        } else {
            toast = Toast.makeText(activity,"Please verify your credentials!",Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
