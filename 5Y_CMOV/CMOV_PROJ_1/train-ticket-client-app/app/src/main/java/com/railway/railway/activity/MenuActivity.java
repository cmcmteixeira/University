package com.railway.railway.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.railway.railway.DI;
import com.railway.railway.R;
import com.railway.railway.activity.listeners.LogoutClick;

/**
 * Created by Leonel on 29/10/2015.
 */
public class MenuActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater(); //from activity
        inflater.inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_used_tickets) {
            Intent intent = new Intent(this, UsedTicketsActivity.class);
            this.startActivity(intent);
        }
        else if(id == R.id.menu_user_schedule){
            Intent intent = new Intent(this, PurchaseSelectStationsActivity.class);
            this.startActivity(intent);
        }
        else if(id == R.id.menu_user_settings){
            Intent intent = new Intent(this, AccountActivity.class);
            this.startActivity(intent);
        }
        else if(id == R.id.menu_user_logout){
            new LogoutClick(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
