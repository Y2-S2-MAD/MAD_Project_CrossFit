package com.example.crossfitappv1;

public class FoodConsumption {

    private String MealName;
    private String  Count;

    public FoodConsumption() {
        this.MealName = "";
        this.Count = "";
    }

    public FoodConsumption(String MealName, String Count) {
        this.MealName = MealName;
        this.Count = Count;
    }

    public String getMealName() {
        return MealName;
    }

    public void setMealName(String mealName) {
        MealName = mealName;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }
}
