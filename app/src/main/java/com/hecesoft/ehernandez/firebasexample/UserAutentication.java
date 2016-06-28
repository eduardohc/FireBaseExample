package com.hecesoft.ehernandez.firebasexample;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ehernandez on 14/06/2016.
 */

public class UserAutentication extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    boolean isUserLogIn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autentication);

        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();

        mAuth = FirebaseAuth.getInstance(); //Initialize auth

        // Start auth state listener
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null) {
                    //If user is signed in
                    Log.d("Autenticacion sign in", "In");
                    Toast.makeText(getApplicationContext(), "User sign in",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserAutentication.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    //Log.d("Result", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    //If user is signed out
                    Log.d("Autenticacion sign up", "In");
                    Toast.makeText(getApplicationContext(), "User sign out",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserAutentication.this, UserRegistration.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    //Log.d("Result", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    /*@Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }*/
}
