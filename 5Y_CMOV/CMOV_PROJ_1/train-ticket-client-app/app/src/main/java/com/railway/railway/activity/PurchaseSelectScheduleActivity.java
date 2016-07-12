package com.railway.railway.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.railway.railway.DI;
import com.railway.railway.R;
import com.railway.railway.activity.listeners.PurchaseActivityScheduleClick;
import com.railway.railway.activity.listeners.SelectScheduleTimeClick;

import java.util.ArrayList;

public class PurchaseSelectScheduleActivity extends MenuActivity {
    ListView scheduleOptionsContainer;

    String departureStation;
    String arrivalStation;
    String dayDepartureTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_select_schedule);

        // Stations passed from previous activity
        this.departureStation = getIntent().getStringExtra("departure");
        this.arrivalStation = getIntent().getStringExtra("arrival");
        this.dayDepartureTime = getIntent().getStringExtra("dayDepartureTime");

        TextView hello = (TextView)findViewById(R.id.purchase_lbl_from_to);
        hello.setText(departureStation + " to " + arrivalStation + " on " + dayDepartureTime);

        double price = DI.get().provideStorage().getSchedule().getPrice(departureStation,arrivalStation);

        this.scheduleOptionsContainer = (ListView)findViewById(R.id.purchase_timetable_listView);
        this.scheduleOptionsContainer.setOnItemClickListener(
                new PurchaseActivityScheduleClick(this, departureStation, arrivalStation, dayDepartureTime, price)
        );
        fillScheduleOptions(departureStation, arrivalStation, dayDepartureTime);

    }

    private void fillScheduleOptions(String departure,String arrival, String date) {
        ArrayList<String> timetable = DI.get().provideStorage().getSchedule().getTimetable(departure, arrival);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timetable);
        this.scheduleOptionsContainer.setAdapter(adapter);

    }

}
