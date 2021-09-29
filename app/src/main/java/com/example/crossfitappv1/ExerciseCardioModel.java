package com.example.crossfitappv1;

public class ExerciseCardioModel {

    private String Exercise_name,Minutes,Calories_Burned;

    public ExerciseCardioModel(){

    }

    public ExerciseCardioModel(String Exercise_name, String Minutes, String Calories_Burned) {
        this.Exercise_name = Exercise_name;
        this.Minutes = Minutes;
        this.Calories_Burned = Calories_Burned;
    }

    public String getExercise_name() {
        return Exercise_name;
    }

    public void setExercise_name(String Exercise_name) {
        Exercise_name = Exercise_name;
    }

    public String getMinutes() {
        return Minutes;
    }

    public void setMinutes(String Minutes) {
        Minutes = Minutes;
    }

    public String getCalories_Burned() {
        return Calories_Burned;
    }

    public void setCalories_Burned(String Calories_Burned) {
        Calories_Burned = Calories_Burned;
    }
}
