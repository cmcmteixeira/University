package com.railway.railway.activity.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.railway.railway.DI;
import com.railway.railway.R;
import com.railway.railway.activity.PurchaseSelectScheduleActivity;
import com.railway.railway.business.api.entity.User;
import com.railway.railway.business.api.request.PurchaseTicketRequestData;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Leonel on 26/10/2015.
 */
public class PurchaseActivityScheduleClick implements AdapterView.OnItemClickListener{
    private String departure;
    private String arrival;
    private String dayDepartureTime;
    private Activity activity;
    private double price;

    public PurchaseActivityScheduleClick(Activity act, String dep, String arr, String day, double price){
        this.departure = dep;
        this.arrival = arr;
        this.activity = act;
        this.dayDepartureTime = day;
        this.price = price;
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

        String cardNumber = DI.get().provideStorage().getUser().getCardNumber();
        String cardType = DI.get().provideStorage().getUser().getCardType();

        // Criar dialog de confirm
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.purchase_confirm_dialog_title);
        builder.setMessage("You are going to purchase a ticket to travel:\n" +
                        "- From " + this.departure + " to " + this.arrival + "\n" +
                        "- Departure: " + dateFormat.format(ts) + "\n" +
                        "- Price: " + this.price + "€\n" +
                        "The payment will be executed with your credit card ending in " +
                        cardNumber.substring(cardNumber.length() - 3) + "\n" +
                        "Are you sure you want to proceed?"
        );

        PurchaseTicketRequestData data = new PurchaseTicketRequestData(arrival,departure,ts.getTime());

        builder.setPositiveButton(R.string.purchase_confirm_dialog_positive, new PurchaseActivityDialogSuccessClick(activity,data));
        builder.setNegativeButton(R.string.purchase_confirm_dialog_negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                // Do nothing. Intended
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
