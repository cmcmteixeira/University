package com.railway.railway.activity.listeners;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.railway.railway.R;
import com.railway.railway.activity.RegisterActivity;
import com.railway.railway.business.api.request.RegisterRequestData;

/**
 * Created by cteixeira on 22-10-2015.
 * com.railway.railway.activity.listeners
 */
public class RegisterActivityRegisterClick implements View.OnClickListener {


    @Override
    public void onClick(View view) {
        RegisterActivity activity = (RegisterActivity) view.getContext();
        TextView username         = (TextView)activity.findViewById(R.id.register_username_input);
        TextView email            = (TextView)activity.findViewById(R.id.register_email_input);
        TextView password         = (TextView)activity.findViewById(R.id.register_password_input);
        TextView creditCardNr     = (TextView)activity.findViewById(R.id.register_card_number_input);
        Spinner  creditCardType   = (Spinner)activity.findViewById(R.id.register_card_type_spinner);
        TextView expiration       = (TextView)activity.findViewById(R.id.register_card_date_input);

        RegisterRequestData data = new RegisterRequestData(
                username.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                creditCardType.getSelectedItem().toString(),
                creditCardNr.getText().toString(),
                expiration.getText().toString()
        );
        new RegisterActivityRegisterTask(view.getContext(),data).execute();
    }
}
