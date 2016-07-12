package com.railway.railway.activity.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.railway.railway.activity.adapter.TicketListAdapter;
import com.railway.railway.business.api.entity.Ticket;

/**
 * Created by Leonel on 23/10/2015.
 */
public class UserActivityTicketClick implements AdapterView.OnItemClickListener{

    public UserActivityTicketClick(){
        int a = 2;
        int c = 4;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        int count = adapterView.getAdapter().getCount();

        for(int j = 0 ; j < count ; j++) {
            if( j == i ){
                continue;
            }
            TicketListAdapter.TicketWrapper _ticket  = (TicketListAdapter.TicketWrapper)adapterView.getItemAtPosition(j);
            _ticket.collpase();
        }
        TicketListAdapter.TicketWrapper ticket = (TicketListAdapter.TicketWrapper) adapterView.getItemAtPosition(i);
        if(!ticket.isExpanded()){
            ticket.expand();
        } else {
            ticket.collpase();
        }
        ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
    }
}
