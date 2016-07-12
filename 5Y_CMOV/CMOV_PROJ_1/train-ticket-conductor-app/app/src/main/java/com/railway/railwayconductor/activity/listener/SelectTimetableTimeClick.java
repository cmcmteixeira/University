package com.railway.railwayconductor.activity.listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

import com.railway.railwayconductor.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Leonel on 08/11/2015.
 */
public class SelectTimetableTimeClick implements AdapterView.OnItemClickListener{
    String departure;
    String arrival;
    String dayDepartureTime;
    Activity activity;

    public SelectTimetableTimeClick(Activity act, String dep, String arr, String dayTimestamp){
        this.activity = act;
        this.dayDepartureTime = dayTimestamp;
        this.departure = dep;
        this.arrival = arr;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String time = (String)parent.getItemAtPosition(position);
        Timestamp ts = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            long milis = dateFormat.parse(this.dayDepartureTime + " " + time).getTime();
            ts = new Timestamp(milis);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        // Criar dialog de confirm
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.select_timetable_dialog_title);
        builder.setMessage("You are going to inspect the following trip:\n" +
                        "- From " + this.departure + " to " + this.arrival + "\n" +
                        "- Departure: " + dateFormat.format(ts) + "\n" +
                        "Are you sure you want to proceed?"
        );

        builder.setPositiveButton(R.string.select_timetable_dialog_confirm,
                new SelectTimetableDialogSuccess(activity, ts, departure, arrival));

        builder.setNegativeButton(R.string.select_timetable_dialog_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                // Do nothing. Intended
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
