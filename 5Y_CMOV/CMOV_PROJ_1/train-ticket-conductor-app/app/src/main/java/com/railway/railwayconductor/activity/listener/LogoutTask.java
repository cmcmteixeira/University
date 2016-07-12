package com.railway.railwayconductor.activity.listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.activity.LoginActivity;

/**
 * Created by Leonel on 07/11/2015.
 */
public class LogoutTask {
    private Activity activity;

    public LogoutTask(Activity ctx){
        this.activity = ctx;
        triggerAlertDialog();
    }

    //TODO: Strings no XML
    private void triggerAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(true);
        builder.setMessage("Do you want to Log Out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DI.get().provideStorage().setToken("");
                DI.get().provideStorage().setUser(null);
                Intent intent = new Intent(activity, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
                activity.finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
