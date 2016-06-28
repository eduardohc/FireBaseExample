package com.hecesoft.ehernandez.firebasexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ehernandez on 20/06/2016.
 */

public class UserSignUp extends AppCompatActivity {

    private int intentNumber;

    private EditText mSignUpEmail;
    private EditText mSignUpPassword;
    private Button btn_signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signup);

        mAuth = FirebaseAuth.getInstance(); //Initialize auth

        mSignUpEmail = (EditText) findViewById(R.id.et_email_signup);
        mSignUpPassword = (EditText) findViewById(R.id.et_password_signup);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;

                email = mSignUpEmail.getText().toString();
                password = mSignUpPassword.getText().toString();

                createAccount(email, password);
            }
        });
    }

    private void createAccount(String email, String password) {
        //Log.d("", "createAccount:" + email);
        /*if (!validateForm()) {
            return;
        }*/

        //showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Log.d("Account status - ", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        Log.d("Sign up", "In");

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            intentNumber = 0;
                        }else{
                            intentNumber = 1;
                            /*Toast.makeText(getApplicationContext(), "User sign up",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UserSignUp.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();*/
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        CallClass(intentNumber);
        // [END create_user_with_email]
    }

    private void CallClass(int intent){
        switch(intent){
            case 0:
                Toast.makeText(UserSignUp.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "User sign up",
                        Toast.LENGTH_SHORT).show();
                Intent activity = new Intent(UserSignUp.this, MainActivity.class);
                activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(activity);
                finish();
                break;
        }
    }
}
