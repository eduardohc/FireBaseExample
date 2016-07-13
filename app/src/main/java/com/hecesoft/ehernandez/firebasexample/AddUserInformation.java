package com.hecesoft.ehernandez.firebasexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ehernandez on 28/06/2016.
 */

public class AddUserInformation extends AppCompatActivity implements View.OnClickListener{

    private EditText et_message;
    private EditText et_name;
    private EditText et_age;
    private EditText et_nacionality;
    private EditText et_gender;

    //FirebaseDatabase database;
    private DatabaseReference mDataBase;
    private DatabaseReference mDataReference;
    private FirebaseUser user; //Reference the user

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_information);

        //database = FirebaseDatabase.getInstance();
        //database.getReference("message");
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        et_message = (EditText) findViewById(R.id.et_message);
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_nacionality = (EditText) findViewById(R.id.et_nacionality);
        et_gender = (EditText) findViewById(R.id.et_gender);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_save_information){
            submitPost();
            Toast.makeText(AddUserInformation.this, "Your information have been saved successfully",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void submitPost(){
        String name, age, nacionality, gender, message;

        //Get the text from forms int add_user_information xml
        name = et_name.getText().toString();
        age = et_age.getText().toString();
        nacionality = et_nacionality.getText().toString();
        gender = et_gender.getText().toString();
        message = et_message.getText().toString();

        String userId = user.getUid(); //Get the user id from firebase database

        writeUserInformation(userId, name, age, nacionality, gender, message);

        Log.d("Message: ", message);

    }

    public void writeUserInformation(String userId, String name, String age, String nacionality,
                                     String gender, String message){
        User user = new User(name, age, nacionality, gender, message);
        mDataBase.child("users").child(userId).child("Personal Information").setValue(user);
    }
}
