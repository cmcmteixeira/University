package com.railway.railwayconductor.activity.listener;

import android.app.DialogFragment;
import android.view.View;

import com.railway.railwayconductor.activity.SelectStationsActivity;
import com.railway.railwayconductor.activity.fragment.DatePickerFragment;

/**
 * Created by Leonel on 07/11/2015.
 */
public class SelectScheduleDatePickerListener implements View.OnClickListener{
    SelectStationsActivity activity;

    public SelectScheduleDatePickerListener(SelectStationsActivity a){
        this.activity = a;
    }

    @Override
    public void onClick(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(activity.getFragmentManager(), "datePicker");
    }
}
