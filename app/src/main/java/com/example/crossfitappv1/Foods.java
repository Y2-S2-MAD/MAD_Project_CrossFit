package com.example.crossfitappv1;

public class Foods  {
    private String MealName;
    private String  Weight;
    private String Calories;
    private String Carbs;
    private String Fat;
    private String Protien;


    public Foods() {
        this.MealName = "";
        this.Weight = "";
        this.Calories = "";
        this.Carbs = "";
        this.Fat = "";
        this.Protien = "";
    }

    public Foods(String mealName, String weight, String calories, String carbs, String fat, String protien) {
        this.MealName = mealName;
        this.Weight = weight;
        this.Calories = calories;
        this.Carbs = carbs;
        this.Fat = fat;
        this.Protien = protien;
    }

    public String getMealName() {
        return MealName;
    }



    public String getWeight() {
        return Weight;
    }

    public String getCalories() {
        return Calories;
    }

    public String getCarbs() {
        return Carbs;
    }

    public String getFat() {
        return Fat;
    }

    public String getProtien() {
        return Protien;
    }

    public void setMealName(String mealName) {
        MealName = mealName;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public void setCarbs(String carbs) {
        Carbs = carbs;
    }

    public void setFat(String fat) {
        Fat = fat;
    }

    public void setProtien(String protien) {
        Protien = protien;
    }
}
