package com.railway.railwayconductor.activity.listener;

import android.view.View;
import android.widget.TextView;

import com.railway.railwayconductor.R;
import com.railway.railwayconductor.activity.LoginActivity;
import com.railway.railwayconductor.business.api.request.AuthRequestData;

/**
 * Created by Leonel on 07/11/2015.
 */
public class LoginActivityLoginClick implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        LoginActivity activity= (LoginActivity) view.getContext();
        activity.disableButtons();
        TextView emailInput= (TextView)activity.findViewById(R.id.login_txt_email);
        TextView passwInput= (TextView)activity.findViewById(R.id.login_txt_password);

        AuthRequestData data = new AuthRequestData(
                emailInput.getText().toString(),
                passwInput.getText().toString()
        );
        new LoginActivityLoginClickTask(
                activity,data
        ).execute();
    }
}