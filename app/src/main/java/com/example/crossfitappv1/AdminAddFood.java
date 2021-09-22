package com.example.crossfitappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddFood extends AppCompatActivity {

    EditText Meal,Weig,Cal,Carbs,Fat,Protein;
    Button Add,Back;
    Foods ObFood;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_food);

        Meal = findViewById(R.id.editMealName);
        Weig = findViewById(R.id.gram);
        Cal = findViewById(R.id.editTxtcalories);
        Carbs = findViewById(R.id.edittxtCarbs);
        Fat = findViewById(R.id.edittxtFat);
        Protein = findViewById(R.id.edittxtProtein);
        Add = findViewById(R.id.btnAdd);
        ObFood = new Foods();

        Back = findViewById(R.id.btnBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void AddFood(View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child("Foods");

        try{

            if (TextUtils.isEmpty(Meal.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Please Enter the Meal Name",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(Weig.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Please Enter the Weight",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(Cal.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Please Enter the Calories",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(Carbs.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Please Enter the Carbs",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(Fat.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Please Enter the Fat",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(Protein.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Please Enter the Protien",Toast.LENGTH_SHORT).show();
            }else {

                ObFood.setMealName(Meal.getText().toString().trim());
                ObFood.setWeight(Weig.getText().toString().trim());
                ObFood.setCalories(Cal.getText().toString().trim());
                ObFood.setCarbs(Carbs.getText().toString().trim());
                ObFood.setFat(Fat.getText().toString().trim());
                ObFood.setProtien(Protein.getText().toString().trim());

                dbRef.push().setValue(ObFood);
                Toast.makeText(getApplicationContext(),"Successfully inserted",Toast.LENGTH_SHORT).show();

            }
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Number Format Exception",Toast.LENGTH_SHORT).show();
        }
    }

}