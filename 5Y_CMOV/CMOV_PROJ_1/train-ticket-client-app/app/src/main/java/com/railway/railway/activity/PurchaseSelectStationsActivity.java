package com.railway.railway.activity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.railway.railway.R;
import com.railway.railway.activity.fragments.DatePickerFragment;
import com.railway.railway.activity.listeners.PurchaseActivityGetStationsTask;
import com.railway.railway.activity.listeners.PurchaseActivitySearchClick;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PurchaseSelectStationsActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_select_stations);

        Button purchaseButton = (Button)findViewById(R.id.purchase_btn_search);
        purchaseButton.setOnClickListener(new PurchaseActivitySearchClick());
        //purchase_btn_search
        new PurchaseActivityGetStationsTask(this).execute();

        ImageButton mPickDate = (ImageButton) findViewById(R.id.purchase_btn_pick_date);
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"datePicker");
            }
        });
        TextView purchaseDate = (TextView) findViewById(R.id.purchase_lbl_date);
        purchaseDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"datePicker");
            }
        });

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) +1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        TextView tv_date = (TextView) findViewById(R.id.purchase_lbl_date);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long milis = dateFormat.parse(year + "-" + month + "-" + day).getTime();
            Date parsedDate = new Date(milis);
            tv_date.setText(parsedDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void addDepartureOptions(ArrayList<String> stations){
        // Departure Spinner
        List<String> departureSpinnerArray =  stations;

        ArrayAdapter<String> departureAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, departureSpinnerArray);

        departureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner departureSpinner = (Spinner) findViewById(R.id.purchase_departure_spinner);
        departureSpinner.setAdapter(departureAdapter);
    }

    public void addArrivalOptions(ArrayList<String> stations){
        // Arrival Spinner
        List<String> arrivalSpinnerArray =  stations;

        ArrayAdapter<String> arrivalAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arrivalSpinnerArray);
        arrivalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner arrivalSpinner = (Spinner) findViewById(R.id.purchase_arrival_spinner);
        arrivalSpinner.setAdapter(arrivalAdapter);
    }

    public String getSelectedArrival(){
        Spinner arrivalSpinner = (Spinner) findViewById(R.id.purchase_arrival_spinner);
        return arrivalSpinner.getSelectedItem().toString();
    }

    public String getSelectedDeparture(){
        Spinner departureSpinner = (Spinner) findViewById(R.id.purchase_departure_spinner);
        return departureSpinner.getSelectedItem().toString();
    }

    public String getSelectedDate(){
        TextView dateTV = (TextView) findViewById(R.id.purchase_lbl_date);
        return dateTV.getText().toString();
    }

}
