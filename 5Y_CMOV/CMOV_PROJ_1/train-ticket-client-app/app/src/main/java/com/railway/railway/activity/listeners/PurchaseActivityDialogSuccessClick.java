package com.railway.railway.activity.listeners;

import android.app.Activity;
import android.content.DialogInterface;

import com.railway.railway.business.api.request.PurchaseTicketRequestData;

/**
 * Created by Leonel on 26/10/2015.
 */
public class PurchaseActivityDialogSuccessClick implements DialogInterface.OnClickListener {


    private Activity activity;
    private PurchaseTicketRequestData data;

    public PurchaseActivityDialogSuccessClick(Activity activity, PurchaseTicketRequestData data) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        new PurchaseActivityPurchaseTicketTask(activity, data).execute();
    }
}
