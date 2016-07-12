package com.railway.railway.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.railway.railway.R;
import com.railway.railway.activity.listeners.RegisterActivityRegisterClick;

/**
 * Created by cteixeira on 18-10-2015.
 * com.railway.railway.activity
 */
public class RegisterActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerBtn = (Button)findViewById(R.id.register_register_button);
        registerBtn.setOnClickListener(new RegisterActivityRegisterClick());
    }
}
