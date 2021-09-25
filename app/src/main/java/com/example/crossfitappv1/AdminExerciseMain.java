package com.example.crossfitappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminExerciseMain extends AppCompatActivity implements View.OnClickListener {

    CardView Cardio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_exercise_main);

        Cardio = findViewById(R.id.cardio_card);

        Cardio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {
            case R.id.cardio_card:
                i = new Intent(this, AdminExerciseCardio.class);
                startActivity(i);
                break;

        }
    }

}