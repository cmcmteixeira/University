package com.railway.railwayconductor.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.railway.railwayconductor.R;
import com.railway.railwayconductor.activity.listener.LogoutTask;
import com.railway.railwayconductor.activity.listener.SelectScheduleDatePickerListener;
import com.railway.railwayconductor.activity.listener.SelectScheduleGetScheduleTask;
import com.railway.railwayconductor.activity.listener.SelectScheduleProceedClick;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SelectStationsActivity extends MenuActivity {
    public TextView lbl_date;
    public ImageButton btn_pick_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_schedule);

        new SelectScheduleGetScheduleTask(this).execute();

        this.btn_pick_date = (ImageButton) findViewById(R.id.select_btn_pick_date);
        this.btn_pick_date.setOnClickListener(new SelectScheduleDatePickerListener(this));
        this.lbl_date = (TextView) findViewById(R.id.select_lbl_date);
        this.lbl_date.setOnClickListener(new SelectScheduleDatePickerListener(this));
        initializeDateLabel();

        Button btn_proceed = (Button) findViewById(R.id.select_btn_proceed);
        btn_proceed.setOnClickListener(new SelectScheduleProceedClick());
    }


    public void addDepartureOptions(ArrayList<String> stations){
        // Departure Spinner
        List<String> departureSpinnerArray =  stations;

        ArrayAdapter<String> departureAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, departureSpinnerArray);

        departureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner departureSpinner = (Spinner) findViewById(R.id.select_spinner_departure);
        departureSpinner.setAdapter(departureAdapter);
    }

    public void addArrivalOptions(ArrayList<String> stations){
        // Arrival Spinner
        List<String> arrivalSpinnerArray =  stations;

        ArrayAdapter<String> arrivalAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arrivalSpinnerArray);
        arrivalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner arrivalSpinner = (Spinner) findViewById(R.id.select_spinner_arrival);
        arrivalSpinner.setAdapter(arrivalAdapter);
    }

    public String getSelectedArrival(){
        Spinner arrivalSpinner = (Spinner) findViewById(R.id.select_spinner_arrival);
        return arrivalSpinner.getSelectedItem().toString();
    }

    public String getSelectedDeparture(){
        Spinner departureSpinner = (Spinner) findViewById(R.id.select_spinner_departure);
        return departureSpinner.getSelectedItem().toString();
    }

    public String getSelectedDate(){
        TextView dateTV = (TextView) findViewById(R.id.select_lbl_date);
        return dateTV.getText().toString();
    }

    private void initializeDateLabel(){
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long milis = dateFormat.parse(year + "-" + month + "-" + day).getTime();
            Date parsedDate = new Date(milis);
            this.lbl_date.setText(parsedDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        new LogoutTask(this);
    }
}
