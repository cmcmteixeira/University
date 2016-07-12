package com.railway.railwayconductor.activity.listener;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import com.railway.railwayconductor.activity.QRCodeReaderActivity;

import java.sql.Timestamp;

/**
 * Created by Leonel on 08/11/2015.
 */
public class SelectTimetableDialogSuccess implements DialogInterface.OnClickListener {


    private Activity activity;
    private String departure;
    private String arrival;
    private Timestamp departureTime;

    public SelectTimetableDialogSuccess(Activity activity, Timestamp ts, String dep, String arr) {
        this.departureTime = ts;
        this.arrival = arr;
        this.departure = dep;
        this.activity = activity;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent intent = new Intent(activity, QRCodeReaderActivity.class);
        intent.putExtra("arrival", arrival);
        intent.putExtra("departure", departure);
        intent.putExtra("departureTime", String.valueOf(departureTime.getTime()));
        activity.startActivity(intent);
        activity.finish();
    }
}