package com.hecesoft.ehernandez.firebasexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/**
 * Created by ehernandez on 28/06/2016.
 */

public class AddUserInformation extends AppCompatActivity implements View.OnClickListener{

    private EditText et_message;

    //FirebaseDatabase database;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_information);

        //database = FirebaseDatabase.getInstance();
        //database.getReference("message");
        mDataBase = FirebaseDatabase.getInstance().getReference();
        et_message = (EditText) findViewById(R.id.et_message);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_save_message){
            submitPost();
        }
    }

    private void submitPost(){
        final String message = et_message.getText().toString();

        mDataBase.setValue(message);
        mDataBase.child("message").push();
        /*mDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        //Log.d("Message", message);
        Toast.makeText(getApplicationContext(), "Your message have been saved successfully",
                Toast.LENGTH_SHORT).show();

    }
}
