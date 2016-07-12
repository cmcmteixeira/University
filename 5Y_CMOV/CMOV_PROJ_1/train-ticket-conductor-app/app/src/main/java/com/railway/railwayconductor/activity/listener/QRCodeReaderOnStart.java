package com.railway.railwayconductor.activity.listener;

import android.os.AsyncTask;

import com.railway.railwayconductor.activity.QRCodeReaderActivity;
import com.railway.railwayconductor.business.api.request.TicketListRequest;
import com.railway.railwayconductor.business.api.request.TicketListRequestData;

/**
 * Created by cteixeira on 02-11-2015.
 *
 */
public class QRCodeReaderOnStart extends AsyncTask<Void, Void, Integer> {



    public QRCodeReaderActivity activity;
    int noTickets;

    public QRCodeReaderOnStart(QRCodeReaderActivity act){
        this.activity = act;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        TicketListRequest request;
        try {
            request = new TicketListRequest(new TicketListRequestData(activity.departure, activity.arrival,activity.timestamp));
            noTickets = request.getResponse();
            return noTickets;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        activity.totalTickets = integer;
        activity.refreshChartData();
    }
}
