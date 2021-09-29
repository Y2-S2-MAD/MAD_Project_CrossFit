package com.example.crossfitappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddFoodMain extends AppCompatActivity {

    Button addFoodToBreakfast,addFoodToLunch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_main);

        addFoodToBreakfast = (Button) findViewById(R.id.btnAddFoodBreakFast);


        addFoodToBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddFoodCount.class));
            }
        });

    }
}