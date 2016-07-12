package com.railway.railway.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.railway.railway.DI;
import com.railway.railway.R;
import com.railway.railway.activity.listeners.LoginActivityLoginClick;
import com.railway.railway.activity.listeners.LoginActivityRegisterClick;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {
    private Button login;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //add handler for login
        this.login    = (Button)findViewById(R.id.email_sign_in_button);
        this.register = (Button)findViewById(R.id.register_button);
        login.setOnClickListener(new LoginActivityLoginClick());
        register.setOnClickListener(new LoginActivityRegisterClick());
        if (DI.get().provideStorage().getUser() != null) {
            Intent intent = new Intent(this, UserActivity.class);
            this.startActivity(intent);
        }


    }

    public void disableButtons(){
        this.login.setEnabled(false);
        this.register.setEnabled(false);
    }

    public void enableButtons(){
        this.login.setEnabled(true);
        this.register.setEnabled(true);
    }

}

