package com.example.crossfitappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ExerciseCardioAdd extends AppCompatActivity {

    EditText Exercise_name,Minutes,Calories_Burned;
    Button btnCardioAdd,btnCardioBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_exercise_cardio_add);

        Exercise_name = (EditText)findViewById(R.id.txtExCardioName);
        Minutes =(EditText)findViewById(R.id.txtExCardioMinutes);
        Calories_Burned = (EditText)findViewById(R.id.txtExCardioCalories);

        btnCardioAdd = (Button)findViewById(R.id.btnCardioAdd);
        btnCardioBack = (Button)findViewById(R.id.btnCardioBack);

        btnCardioAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               insertCardioData();
               clearAllCardio();
            }
        });

        btnCardioBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void insertCardioData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("Exercise_name",Exercise_name.getText().toString());
        map.put("Minutes",Minutes.getText().toString());
        map.put("Calories_Burned",Calories_Burned.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("CardioExercises").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ExerciseCardioAdd.this,"Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ExerciseCardioAdd.this,"Error while Insertion",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAllCardio()
    {
        Exercise_name.setText("");
        Minutes.setText("");
        Calories_Burned.setText("");
    }

}