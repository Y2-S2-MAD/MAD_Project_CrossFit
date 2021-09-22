package com.example.crossfitappv1;


public class ModelFoods {

    String MealName,Weight,Calories,Carbs,Fat,Protien;

    public ModelFoods() {
    }

    public ModelFoods(String mealName, String weight, String calories, String carbs, String fat, String protien) {
        MealName = mealName;
        Weight = weight;
        Calories = calories;
        Carbs = carbs;
        Fat = fat;
        Protien = protien;
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
}
