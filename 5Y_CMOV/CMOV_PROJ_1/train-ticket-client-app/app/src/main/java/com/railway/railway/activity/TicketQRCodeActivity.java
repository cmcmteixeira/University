package com.railway.railway.activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.railway.railway.DI;
import com.railway.railway.R;
import com.railway.railway.business.api.entity.Ticket;

import net.glxn.qrgen.android.QRCode;

public class TicketQRCodeActivity extends MenuActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        setContentView(R.layout.activity_ticket_qrcode);

        Ticket ticket = (Ticket)getIntent().getSerializableExtra("ticket");
        TextView ticketInfo = (TextView)findViewById(R.id.ticketqrcode_lbl_ticket);
        String qrCodeInfo  = DI.get().provideGSON().toJson(ticket);

        Bitmap myBitmap = QRCode.from(qrCodeInfo).bitmap();
        ImageView myImage = (ImageView) findViewById(R.id.ticketqrcode_qrcode_image);
        myImage.setImageBitmap(myBitmap);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ticketqrcode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
