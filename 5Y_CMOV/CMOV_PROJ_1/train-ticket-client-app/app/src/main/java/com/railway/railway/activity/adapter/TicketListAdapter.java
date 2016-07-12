package com.railway.railway.activity.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.railway.railway.DI;
import com.railway.railway.R;
import com.railway.railway.activity.views.TicketListRowView;
import com.railway.railway.business.api.entity.Ticket;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.AbstractQRCode;
import net.glxn.qrgen.core.image.ImageType;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cteixeira on 31-10-2015.
 * com.railway.railway.activity.adapter
 */
public class TicketListAdapter extends BaseAdapter {
    public class TicketWrapper{

        private Ticket ticket;
        private boolean expanded = false;

        TicketWrapper(Ticket ticket){
            this.ticket = ticket;
            this.expanded = false;
        }

        public Ticket getTicket(){
            return this.ticket;
        }

        public TicketWrapper setTicket(Ticket ticket){
            this.ticket = ticket;
            return this;

        }
        public TicketWrapper expand(){
            this.expanded = true;
            return this;
        }

        public boolean isExpanded(){
            return this.expanded;
        }

        public TicketWrapper collpase(){
            this.expanded = false;
            return this;
        }


        private TicketWrapper setRefreshed(){
            return this;
        }

    }


    List<TicketWrapper> tickets;

    public TicketListAdapter(){
        this.tickets = new ArrayList<TicketWrapper>();
    }



    public void add(Ticket ticket){
        this.tickets.add(new TicketWrapper(ticket));
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public TicketWrapper getItem(int position) {
        return  this.tickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.getItem(position).ticket.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TicketWrapper ticket = this.getItem(position);

        if(convertView == null) {
            String qrCodeInfo  = DI.get().provideGSON().toJson(ticket.ticket);
            WindowManager wm = (WindowManager) DI.get().provideAppContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;

            AbstractQRCode qr = QRCode.from(qrCodeInfo).withSize(width-50, width-50).to(ImageType.PNG);
            byte[] qrcodeBytes = qr.stream().toByteArray();
            Bitmap qrcode = BitmapFactory.decodeByteArray(qrcodeBytes,0,qrcodeBytes.length);
            TicketListRowView view = new TicketListRowView(
                    parent.getContext()
            );
            ImageView image = (ImageView)view.findViewById(R.id.ticketlistitem_qrcode);
            image.setImageBitmap(qrcode);
            view.setTicket(tickets.get(position).ticket);
            return view;
        }
        int c = 8;
        int k = 8;

        ImageView image = (ImageView)convertView.findViewById(R.id.ticketlistitem_qrcode);
        ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
        if(ticket.expanded){
            layoutParams.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            layoutParams.height = 0;
            layoutParams.width = 0;
        }
        image.setLayoutParams(layoutParams);
        return convertView;
    }

}
