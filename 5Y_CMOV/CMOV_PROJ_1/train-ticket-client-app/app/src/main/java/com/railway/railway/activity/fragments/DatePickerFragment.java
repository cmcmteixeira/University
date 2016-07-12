package com.railway.railway.activity.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.railway.railway.R;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Leonel on 28/10/2015.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        TextView tv_date = (TextView) this.getActivity().findViewById(R.id.purchase_lbl_date);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long milis = dateFormat.parse(year + "-" + month + "-" + day).getTime();
            Date parsedDate = new Date(milis);
            tv_date.setText(parsedDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        TextView tv_date = (TextView) this.getActivity().findViewById(R.id.purchase_lbl_date);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long milis = dateFormat.parse(year + "-" + (monthOfYear+1) + "-" + dayOfMonth).getTime();
            Date parsedDate = new Date(milis);
            tv_date.setText(parsedDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

