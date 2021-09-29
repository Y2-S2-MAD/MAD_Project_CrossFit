package com.example.crossfitappv1;



public class Users {
    public String fullname;
    public String email;
    public String dob;
    public String CurWeight;
    public String GoalWeight;
    public String height;
    public String male;
    public String female;
    public String user;
    public String bmr;

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

    public String getbmr() {
        return bmr;
    }

    public void setbmr(String bmr) {
        this.bmr = bmr;
    }

    /*public String getbmrf() {
        return bmrf;
    }*/

    /*public void setbmrf(String bmrf) {
        this.bmrf = bmrf;
    }*/

    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return user;
    }

}
