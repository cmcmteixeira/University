package com.railway.railway.activity.listeners;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.railway.railway.DI;
import com.railway.railway.R;
import com.railway.railway.activity.UserActivity;
import com.railway.railway.activity.adapter.TicketListAdapter;
import com.railway.railway.business.api.entity.Ticket;
import com.railway.railway.business.api.request.MyTicketsRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leonel on 22/10/2015.
 *
 */
public class UserActivityMyTicketsTask extends AsyncTask<Void, Void, JSONObject> {

    private UserActivity activity;

    public UserActivityMyTicketsTask(Activity activity){
        this.activity = (UserActivity) activity;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        try {
            MyTicketsRequest request = new MyTicketsRequest(true);
            return request.getResponse();
        } catch (JSONException | InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result)  {
        ListView tickets = (ListView) this.activity.findViewById(R.id.user_ticketList_listView);
        TicketListAdapter adapter = new TicketListAdapter();
        try{
            JSONArray jsonTickets = (JSONArray) result.get("active");
            ArrayList<Ticket> ticketsParsed = new ArrayList<>();
            for(int i = 0 ; i < jsonTickets.length(); i++){
                Ticket ticket = new Ticket((JSONObject)jsonTickets.get(i));
                ticketsParsed.add(ticket);
            }
            DI.get().provideStorage().setTickets(ticketsParsed);
        } catch (Exception e){

        }
        for(Ticket ticket:DI.get().provideStorage().getTickets()) {
            adapter.add(ticket);
        }
        tickets.setAdapter(adapter);
    }

}
;