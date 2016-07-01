package com.hecesoft.ehernandez.firebasexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Create the views
    //private Button btn_logout;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_logout:
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, UserRegistration.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_addInformation:
                startActivity(new Intent(MainActivity.this, AddUserInformation.class));
                break;
            case R.id.btn_getInformation:
                startActivity(new Intent(MainActivity.this, GetUserInformation.class));
                break;
        }
    }
}
