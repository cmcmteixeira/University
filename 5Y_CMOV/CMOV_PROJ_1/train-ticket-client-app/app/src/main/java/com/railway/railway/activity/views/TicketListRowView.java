package com.railway.railway.activity.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.railway.railway.R;
import com.railway.railway.business.api.entity.Ticket;


public class TicketListRowView extends LinearLayout {

    public TicketListRowView(Context context){
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ticket_list_item, this);
    }

    public TicketListRowView setTicket(Ticket ticket) {
        TextView date   = (TextView)this.findViewById(R.id.ticketlistitem_date);
        TextView fromTo = (TextView)this.findViewById(R.id.ticketlistitem_fromto);
        fromTo.setText(ticket.getDeparture() + " - " + ticket.getArrival());
        date.setText(ticket.getDepartureDateTimeFormatted() + " - " +ticket.getPrice() + "€");
        return this;
    }
}
