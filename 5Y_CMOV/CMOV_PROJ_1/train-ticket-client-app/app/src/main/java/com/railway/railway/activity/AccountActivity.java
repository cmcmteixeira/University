package com.railway.railway.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.railway.railway.DI;
import com.railway.railway.R;
import com.railway.railway.activity.listeners.AccountActivityEditClick;
import com.railway.railway.business.api.entity.User;

import java.util.Arrays;

public class AccountActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    EditText confirmPassword;
    EditText cardNumber;
    EditText cardValidity;
    Spinner cardType;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        User user = DI.get().provideStorage().getUser();

        this.username = (EditText) findViewById(R.id.account_input_username);
        this.email = (EditText) findViewById(R.id.account_input_email);
        this.password = (EditText) findViewById(R.id.account_input_password);
        this.confirmPassword = (EditText) findViewById(R.id.account_input_confirm_password);
        this.cardNumber = (EditText) findViewById(R.id.account_input_card_number);
        this.cardValidity = (EditText) findViewById(R.id.account_input_card_date);
        this.cardType = (Spinner) findViewById(R.id.account_input_card_type);

        this.username.setText(user.getName());
        this.email.setText(user.getEmail());
        this.cardNumber.setText(user.getCardNumber());
        this.cardValidity.setText(user.getCardExpiration());
        this.cardType.setSelection(Arrays.asList(getResources().getStringArray(R.array.credit_card_types)).indexOf(user.getCardType()));

        this.submitButton = (Button) findViewById(R.id.account_submit_button);
        submitButton.setOnClickListener(new AccountActivityEditClick());
    }


}
