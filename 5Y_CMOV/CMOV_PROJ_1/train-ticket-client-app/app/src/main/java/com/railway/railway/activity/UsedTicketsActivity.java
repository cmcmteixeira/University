package com.railway.railway.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.railway.railway.R;
import com.railway.railway.activity.listeners.UsedTicketsActivityUsedTicketsTask;
import com.railway.railway.business.api.entity.Ticket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsedTicketsActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_tickets);

        new UsedTicketsActivityUsedTicketsTask(this).execute();
    }

    public void fillUsedTickets(JSONObject tickets) throws JSONException {
        LinearLayout ticketsContainer = (LinearLayout) findViewById(R.id.used_tickets_container);

        JSONArray usedTickets = (JSONArray) tickets.get("used");

        if(usedTickets.length() == 0){
            TextView tv_ticket = new TextView(this);
            tv_ticket.setGravity(Gravity.LEFT);
            tv_ticket.setText("You have no used tickets yet!");
            ticketsContainer.addView(tv_ticket);
        }
        for(int i = 0; i < usedTickets.length(); i++){
            Ticket currentTicket = new Ticket(usedTickets.getJSONObject(i));

            TextView tv_ticket = new TextView(this);
            tv_ticket.setId(i);
            tv_ticket.setGravity(Gravity.LEFT);
            tv_ticket.setText(
                    "From: "
                    + currentTicket.getDeparture() + "\n"
                    + "To: "
                    + currentTicket.getArrival() + "\n"
                    + currentTicket.getDepartureDateTimeFormatted() + "\n"
                    + currentTicket.getPrice() + "€"
            );
            ticketsContainer.addView(tv_ticket);
        }

    }
}
