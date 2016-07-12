package com.railway.railwayconductor.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.railway.railwayconductor.R;
import com.railway.railwayconductor.activity.listener.LogoutTask;
import com.railway.railwayconductor.activity.listener.ValidateTicketsTask;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
        else if(id == R.id.menu_logout){
            new LogoutTask(this);
        }
        else if(id == R.id.menu_validate){
            new ValidateTicketsTask(this).execute();
        }


        return super.onOptionsItemSelected(item);
    }
}
