package com.railway.railway.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.railway.railway.R;
import com.railway.railway.activity.listeners.UserActivityMyTicketsTask;
import com.railway.railway.activity.listeners.LogoutClick;
import com.railway.railway.activity.listeners.UserActivityPurchaseClick;
import com.railway.railway.activity.listeners.UserActivityTicketClick;

public class UserActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Button purchaseButton = (Button)findViewById(R.id.user_btn_purchase);
        purchaseButton.setOnClickListener(new UserActivityPurchaseClick());
        ListView lv = (ListView)findViewById(R.id.user_ticketList_listView);
        lv.setOnItemClickListener(new UserActivityTicketClick());

        //boolean forceCall = false;
        //if(getIntent().hasExtra("forceCall")) forceCall=true;
        new UserActivityMyTicketsTask(this).execute();

    }


    @Override
    public void onBackPressed() {
        new LogoutClick(this);
    }
}
