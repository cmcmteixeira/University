package com.railway.railwayconductor.activity.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.railway.railwayconductor.R;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Leonel on 07/11/2015.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    TextView lbl_date;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        this.lbl_date = (TextView) getActivity().findViewById(R.id.select_lbl_date);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long milis = dateFormat.parse(year + "-" + (month+1) + "-" + day).getTime();
            Date parsedDate = new Date(milis);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long milis = dateFormat.parse(year + "-" + (monthOfYear+1) + "-" + dayOfMonth).getTime();
            Date parsedDate = new Date(milis);
            this.lbl_date.setText(parsedDate.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
