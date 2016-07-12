package com.railway.railway.activity.listeners;

import android.content.Intent;
import android.view.View;

import com.railway.railway.activity.PurchaseSelectScheduleActivity;
import com.railway.railway.activity.PurchaseSelectStationsActivity;

/**
 * Created by Leonel on 24/10/2015.
 */
public class PurchaseActivitySearchClick implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        PurchaseSelectStationsActivity activity = (PurchaseSelectStationsActivity) view.getContext();

        Intent intent = new Intent(activity, PurchaseSelectScheduleActivity.class);
        intent.putExtra("departure", activity.getSelectedDeparture());
        intent.putExtra("arrival", activity.getSelectedArrival());
        intent.putExtra("dayDepartureTime",activity.getSelectedDate());
        activity.startActivity(intent);
    }
}
