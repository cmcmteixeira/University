package com.railway.railwayconductor.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.R;
import com.railway.railwayconductor.activity.listener.LogoutTask;
import com.railway.railwayconductor.activity.listener.QRCodeReaderOnStart;
import com.railway.railwayconductor.activity.listener.QRCodeReaderOnVerifyClick;
import com.railway.railwayconductor.activity.listener.ValidateTicketsTask;
import com.railway.railwayconductor.business.api.entity.Ticket;
import com.railway.railwayconductor.business.api.storage.Storage.AlreadyExists;
import com.railway.railwayconductor.business.security.signature.SignatureValidator;
import com.railway.railwayconductor.business.security.TicketValidator;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class QRCodeReaderActivity extends MenuActivity {
    // Esta viagem tem 2 bilhetes
    public String departure = "Station A";
    public String arrival = "Station B";
    public String timestamp = "1422820800000";

    public PieChart chart;
    public int totalTickets;
    public int usedTickets;
    TextView infoTrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_reader);
        this.chart = (PieChart)findViewById(R.id.chart);
        this.arrival = getIntent().getStringExtra("arrival");
        this.departure = getIntent().getStringExtra("departure");
        this.timestamp = getIntent().getStringExtra("departureTime");
        this.infoTrip = (TextView) findViewById(R.id.result);
        this.infoTrip.setText(departure + " to " + arrival + " on "
                + new SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.UK).format(new Timestamp(Long.parseLong(timestamp))));

        new QRCodeReaderOnStart(this).execute();
        findViewById(R.id.qrcodereader_verify_button).setOnClickListener(new QRCodeReaderOnVerifyClick());
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String message = "";
        int icon = 0;
        try{
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanResult != null) {
                Ticket ticket = new Ticket(new JSONObject(scanResult.getContents()));
                boolean validate;
                if(ticket.getDeparture().equals(this.departure) &&
                        ticket.getArrival().equals(this.arrival) &&
                        ticket.getDepartureTimeTimestamp().equals(this.timestamp)
                        ){
                    TicketValidator secureTicket = new TicketValidator(ticket,DI.get().provideStorage());
                    validate = secureTicket.isValid();
                    message = validate ? "Valid Ticket" : "Invalid Ticket";
                }
                else{
                    validate = false;
                    message = "This ticket can't be used on this trip";
                }

                icon = validate ? R.drawable.valid : R.drawable.invalid;
                if(validate){
                    DI.get().provideStorage().addValidatedTicketID(Integer.toString(ticket.getId()));
                }
            }
        } catch(AlreadyExists e) {
            icon = R.drawable.danger;
            message = "This ticket was already validated";
        }
        catch (Exception e) {
            icon = R.drawable.invalid;
            message = "Something went wrong";
        } finally {
            new AlertDialog.Builder(this)
                    .setTitle("Ticket")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(icon)
                    .show();
            refreshChartData();
        }

    }

    public void refreshChartData(){
        this.usedTickets = DI.get().provideStorage().getValidatedIDs().size();

        PieDataSet dataSet = new PieDataSet(
                new ArrayList<>(Arrays.asList(
                        new Entry(totalTickets-usedTickets,0),
                        new Entry(usedTickets,1)
                )),
                ""
        );
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(Arrays.asList(Color.rgb(136, 170, 255), Color.rgb(239, 155, 15)));

        this.chart.setData(new PieData(
                new ArrayList<>(Arrays.asList(
                        "Total",
                        "Validated")
                ),
                dataSet
        ));
        //this.chart.notifyDataSetChanged();
        this.chart.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_with_validate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_select_schedule) {
            Intent intent = new Intent(this, SelectStationsActivity.class);
            this.startActivity(intent);
            this.finish();
        }
        else if(id == R.id.menu_validate){
            new ValidateTicketsTask(this).execute();
        }
        else if(id == R.id.menu_logout){
            new LogoutTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
