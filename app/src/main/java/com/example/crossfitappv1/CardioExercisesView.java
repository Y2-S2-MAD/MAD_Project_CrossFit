package com.example.crossfitappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CardioExercisesView extends AppCompatActivity {

    RecyclerView recyclerView;
    UserViewCardioAdapter userViewCardioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_exercises_view);

        recyclerView = (RecyclerView)findViewById(R.id.userViewCardioExercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ExerciseCardioModel> options =
                new FirebaseRecyclerOptions.Builder<ExerciseCardioModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("CardioExercises"),ExerciseCardioModel.class)
                .build();

        userViewCardioAdapter = new UserViewCardioAdapter(options);
        recyclerView.setAdapter(userViewCardioAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        userViewCardioAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userViewCardioAdapter.stopListening();
    }
}