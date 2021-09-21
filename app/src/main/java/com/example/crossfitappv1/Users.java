package com.example.crossfitappv1;

import com.google.firebase.firestore.auth.User;

public class Users {
    public String fullname,email,dob,CurWeight,GoalWeight,height,male,female,user;

    public Users(){

    }
    public Users(String fullname,String email,String dob,String CurWeight,String GoalWeight,String height){
        this.fullname = fullname;
        this.email = email;
        this.dob = dob;
        this.CurWeight = CurWeight;
        this.GoalWeight = GoalWeight;
        this.height = height;

    }

    public String getMale() {
        return male;
    }

    public String getFemale() {
        return female;
    }
    public void setMale(String male){
        this.male = male;
    }
    public void setFemale(String female){
        this.female = female;
    }
    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return user;
    }

}
