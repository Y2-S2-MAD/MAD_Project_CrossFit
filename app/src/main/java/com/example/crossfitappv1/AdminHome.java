package com.example.crossfitappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;

//comment comment
public class AdminHome extends AppCompatActivity {

import android.view.View;

public class AdminHome extends AppCompatActivity implements View.OnClickListener {


    CardView AdminExercise;

    CardView AdminFoodCard;


    CardView AdminStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        AdminExercise = (CardView) findViewById(R.id.admin_exercise_card);

        AdminExercise.setOnClickListener(this);

        AdminStore = (CardView) findViewById(R.id.store_card);

        AdminStore.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {
            case R.id.admin_exercise_card:
                i = new Intent(this, AdminExerciseMain.class);
                startActivity(i);
                break;

            case R.id.store_card:
                i = new Intent(this, AdminStoreCategory.class);
                startActivity(i);
                break;

        }

        AdminFoodCard = findViewById(R.id.food_card);
        AdminFoodCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this,AdminFoodMain.class));


            }
        });

    }
}