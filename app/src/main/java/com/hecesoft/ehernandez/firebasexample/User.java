package com.hecesoft.ehernandez.firebasexample;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by ehernandez on 14/06/2016.
 */

@IgnoreExtraProperties
public class User {

    //Name and adress string
    public String username;
    public String email;

    public User(){}

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    /*public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/
}
