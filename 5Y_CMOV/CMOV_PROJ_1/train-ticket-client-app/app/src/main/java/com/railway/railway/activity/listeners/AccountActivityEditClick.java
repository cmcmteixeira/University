package com.railway.railway.activity.listeners;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.railway.railway.R;
import com.railway.railway.activity.AccountActivity;
import com.railway.railway.activity.RegisterActivity;
import com.railway.railway.business.api.request.RegisterRequestData;

/**
 * Created by Leonel on 18/11/2015.
 */
public class AccountActivityEditClick implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        AccountActivity activity = (AccountActivity) view.getContext();
        TextView username         = (TextView)activity.findViewById(R.id.account_input_username);
        TextView email            = (TextView)activity.findViewById(R.id.account_input_email);
        TextView password         = (TextView)activity.findViewById(R.id.account_input_password);
        TextView confirmPassword  = (TextView)activity.findViewById(R.id.account_input_confirm_password);
        TextView creditCardNr     = (TextView)activity.findViewById(R.id.account_input_card_number);
        Spinner creditCardType   = (Spinner)activity.findViewById(R.id.account_input_card_type);
        TextView expiration       = (TextView)activity.findViewById(R.id.account_input_card_date);

        if(!password.getText().toString().equals(confirmPassword.getText().toString())){
            Toast toast = Toast.makeText(view.getContext(), "Password and Confirm Password fields don't match.", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        RegisterRequestData data = new RegisterRequestData(
                username.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                creditCardType.getSelectedItem().toString(),
                creditCardNr.getText().toString(),
                expiration.getText().toString()
        );
        new AccountActivityEditTask(view.getContext(),data).execute();
    }
}
