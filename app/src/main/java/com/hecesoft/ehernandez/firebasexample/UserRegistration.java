package com.hecesoft.ehernandez.firebasexample;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ehernandez on 20/06/2016.
 */

public class UserRegistration extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_signin_activity){
            startActivity(new Intent(UserRegistration.this, UserSignIn.class));
        }

        if(v.getId() == R.id.btn_signup_activity){
            startActivity(new Intent(UserRegistration.this, UserSignUp.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
