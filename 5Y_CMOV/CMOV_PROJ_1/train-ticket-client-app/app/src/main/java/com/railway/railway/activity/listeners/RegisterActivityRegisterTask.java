package com.railway.railway.activity.listeners;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.railway.railway.activity.UserActivity;
import com.railway.railway.business.api.request.RegisterRequest;
import com.railway.railway.business.api.request.RegisterRequestData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by cteixeira on 22-10-2015.
 * com.railway.railway.activity.listeners
 */
public class RegisterActivityRegisterTask extends AsyncTask<Void, Void, JSONObject> {

    private Context context;
    private RegisterRequestData data;
    private RegisterRequest request;

    RegisterActivityRegisterTask(Context context, RegisterRequestData data){
        this.context = context;
        this.data = data;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        try {
            this.request = new RegisterRequest(data);
            return this.request.getResponse();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        Toast toast;
        if(result == null){
            toast = Toast.makeText(this.context,"There was an error registering the user.",Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(this.context,"Register completed.",Toast.LENGTH_LONG);
            Intent intent = new Intent(context, UserActivity.class);
            context.startActivity(intent);
        }
        toast.show();
    }
}
