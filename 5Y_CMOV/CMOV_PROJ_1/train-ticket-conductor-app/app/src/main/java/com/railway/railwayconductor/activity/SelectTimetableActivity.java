package com.railway.railwayconductor.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.R;
import com.railway.railwayconductor.activity.listener.SelectTimetableTimeClick;

import java.util.ArrayList;

public class SelectTimetableActivity extends MenuActivity {
    ListView scheduleOptionsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_table);

        // Info passed from previous activity
        String departureStation = getIntent().getStringExtra("departure");
        String arrivalStation = getIntent().getStringExtra("arrival");
        String departureTime = getIntent().getStringExtra("dayDepartureTime");

        this.scheduleOptionsContainer = (ListView)findViewById(R.id.user_ticketList_listView);
        this.scheduleOptionsContainer.setOnItemClickListener(
                new SelectTimetableTimeClick(this, departureStation, arrivalStation, departureTime)
        );

        fillScheduleOptions(departureStation,arrivalStation,departureTime);
    }

    private void fillScheduleOptions(String departure, String arrival, String departureTime){

        ArrayList<String> timetable = DI.get().provideStorage().getSchedule().getTimetable(departure, arrival);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timetable);
        this.scheduleOptionsContainer.setAdapter(adapter);
    }

}
