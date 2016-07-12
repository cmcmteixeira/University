package com.railway.railway.activity.listeners;

import android.content.Intent;
import android.view.View;

import com.railway.railway.activity.RegisterActivity;

/**
 * Created by cteixeira on 10-10-2015.
 */
public class LoginActivityRegisterClick implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), RegisterActivity.class);
        v.getContext().startActivity(intent);
    }
}
