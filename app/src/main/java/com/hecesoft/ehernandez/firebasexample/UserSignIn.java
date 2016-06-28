package com.hecesoft.ehernandez.firebasexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

public class UserSignIn extends AppCompatActivity {

    int intentNumber;

    private EditText mSignInEmail;
    private EditText mSignInPassword;
    private Button btn_signIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_singin);

        mAuth = FirebaseAuth.getInstance(); //Initialize auth

        mSignInEmail = (EditText) findViewById(R.id.et_email_signin);
        mSignInPassword = (EditText) findViewById(R.id.et_password_signin);
        btn_signIn = (Button) findViewById(R.id.btn_signin);

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;

                if(validateForm()){
                    email = mSignInEmail.getText().toString();
                    password = mSignInPassword.getText().toString();

                    signIn(email, password);
                }
            }
        });
    }

    private void signIn(String email, String password) {
        Toast.makeText(getApplicationContext(), "Authenticating user",
                Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Sign in", "In");

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            intentNumber = 0;
                        }else{
                            intentNumber = 1;
                        }
                    }
                });
        CallClass(intentNumber);
    }

    private void CallClass(int intent){
        switch(intent){
            case 0:
                Toast.makeText(UserSignIn.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "User sign up",
                        Toast.LENGTH_SHORT).show();
                Intent activity = new Intent(UserSignIn.this, MainActivity.class);
                activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(activity);
                finish();
                break;
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mSignInEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mSignInEmail.setError("Required.");
            valid = false;
        } else {
            mSignInEmail.setError(null);
        }

        String password = mSignInPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mSignInPassword.setError("Required.");
            valid = false;
        } else {
            mSignInPassword.setError(null);
        }

        return valid;
    }
}
