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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ehernandez on 20/06/2016.
 */

public class UserSignUp extends AppCompatActivity {

    private int intentNumber;

    private EditText mSignUpEmail;
    private EditText mSignUpPassword;
    private Button btn_signup;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signup);

        mDatabase = FirebaseDatabase.getInstance().getReference();
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
                            onAuthSuccess(task.getResult().getUser());
                            intentNumber = 1;
                        }
                    }
                });
        CallClass(intentNumber);
        // [END create_user_with_email]
    }

    private void onAuthSuccess(FirebaseUser user){
        String username = usernameFromEmail(user.getEmail());
        writeNewUser(user.getUid(), username, user.getEmail());
        Log.d("User ID",user.getUid());
        Log.d("Username",username);
        Log.d("Email",user.getEmail());
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        mDatabase.child("users").child(userId).setValue(user);
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
