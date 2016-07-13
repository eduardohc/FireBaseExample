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
    public String name;
    public String age;
    public String nacinoality;
    public String gender;
    public String message;

    public User(){}

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public User(String name, String age, String nacionality, String gender, String message){
        this.name = name;
        this.age = age;
        this.nacinoality = nacionality;
        this.gender = gender;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNacinoality() {
        return nacinoality;
    }

    public void setNacinoality(String nacinoality) {
        this.nacinoality = nacinoality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

