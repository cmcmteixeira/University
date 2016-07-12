package com.railway.railwayconductor.activity.listener;

import android.content.Intent;
import android.view.View;

import com.railway.railwayconductor.activity.SelectStationsActivity;
import com.railway.railwayconductor.activity.SelectTimetableActivity;

/**
 * Created by Leonel on 07/11/2015.
 */
public class SelectScheduleProceedClick implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        SelectStationsActivity activity = (SelectStationsActivity) v.getContext();

        Intent intent = new Intent(activity, SelectTimetableActivity.class);
        intent.putExtra("departure", activity.getSelectedDeparture());
        intent.putExtra("arrival", activity.getSelectedArrival());
        intent.putExtra("dayDepartureTime",activity.getSelectedDate());
        activity.startActivity(intent);
        activity.finish();
    }
}
