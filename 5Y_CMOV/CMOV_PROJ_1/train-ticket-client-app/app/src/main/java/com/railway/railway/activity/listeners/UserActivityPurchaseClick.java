package com.railway.railway.activity.listeners;

import android.content.Intent;
import android.view.View;

import com.railway.railway.activity.PurchaseSelectStationsActivity;
import com.railway.railway.activity.UserActivity;

/**
 * Created by Leonel on 22/10/2015.
 */
public class UserActivityPurchaseClick implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        UserActivity activity = (UserActivity) view.getContext();
        Intent intent = new Intent(activity, PurchaseSelectStationsActivity.class);
        activity.startActivity(intent);
    }
}
