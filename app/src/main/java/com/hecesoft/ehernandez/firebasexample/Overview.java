package com.hecesoft.ehernandez.firebasexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ehernandez on 24/06/2016.
 */

public class Overview extends AppCompatActivity{

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
                } finally{
                    CallUserRegistration();
                }
            }
        };
        timer.start();
    }

    private void CallUserRegistration(){
        startActivity(new Intent(Overview.this, UserAutentication.class));
        finish();
    }
}
