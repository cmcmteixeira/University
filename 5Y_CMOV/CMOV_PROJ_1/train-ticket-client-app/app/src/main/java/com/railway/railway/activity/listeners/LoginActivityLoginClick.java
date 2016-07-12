package com.railway.railway.activity.listeners;

import android.view.View;
import android.widget.TextView;

import com.railway.railway.R;
import com.railway.railway.activity.LoginActivity;
import com.railway.railway.business.api.request.AuthRequestData;

public class LoginActivityLoginClick implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        LoginActivity activity= (LoginActivity) view.getContext();
        activity.disableButtons();
        TextView emailInput= (TextView)activity.findViewById(R.id.register_email);
        TextView passwInput= (TextView)activity.findViewById(R.id.register_password);

        AuthRequestData data = new AuthRequestData(
                emailInput.getText().toString(),
                passwInput.getText().toString()
        );
        new LoginActivityLoginClickTask(
                activity,data
        ).execute();
    }
}
