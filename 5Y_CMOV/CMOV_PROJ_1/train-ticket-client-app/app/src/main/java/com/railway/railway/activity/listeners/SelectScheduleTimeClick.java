package com.railway.railway.activity.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

import com.railway.railway.DI;
import com.railway.railway.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Leonel on 08/11/2015.
 */
public class SelectScheduleTimeClick implements AdapterView.OnItemClickListener {
    String departure;
    String arrival;
    String dayDepartureTime;
    Activity activity;

    public SelectScheduleTimeClick(Activity act, String dep, String arr, String dayTimestamp) {
        this.activity = act;
        this.dayDepartureTime = dayTimestamp;
        this.departure = dep;
        this.arrival = arr;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //String time = (String)parent.getItemAtPosition(position);
        //Timestamp ts = null;
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
        //try {
        //    long milis = dateFormat.parse(this.dayDepartureTime + " " + time).getTime();
        //    ts = new Timestamp(milis);
//
        //} catch (ParseException e) {
        //    e.printStackTrace();
        //}
//
        //String cardNumber = DI.get().provideStorage().getUser().getCardNumber();
        //String cardType = DI.get().provideStorage().getUser().getCardType();
//
        //// Criar dialog de confirm
        //AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //builder.setTitle(R.string.purchase_confirm_dialog_title);
        //builder.setMessage("You are going to purchase a ticket to travel:\n" +
        //                "- From " + this.departure + " to " + this.arrival + "\n" +
        //                "- Departure: " + dateFormat.format(ts) + "\n" +
        //                "- Price: " + this.price + "€\n" +
        //                "The payment will be executed with your credit card ending in " +
        //                cardNumber.substring(cardNumber.length() - 3) + "\n" +
        //                "Are you sure you want to proceed?"
        //);
//
        //builder.setPositiveButton(R.string.select_timetable_dialog_confirm,
        //        new SelectTimetableDialogSuccess(activity, ts, departure, arrival));
//
        //builder.setNegativeButton(R.string.select_timetable_dialog_cancel, new DialogInterface.OnClickListener() {
        //    public void onClick(DialogInterface dialog, int id) {
        //        // User cancelled the dialog
        //        // Do nothing. Intended
        //    }
        //});
//
        //AlertDialog dialog = builder.create();
        //dialog.show();
    }
}
