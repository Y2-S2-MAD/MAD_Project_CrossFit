package com.example.crossfitappv1;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AdminExerciseCardio extends AppCompatActivity {

    RecyclerView recyclerView;
    ExerciseCardioAdapter exerciseCardioAdapter;
    Button addCardioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_exercise_cardio);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ExerciseCardioModel> options =
                new FirebaseRecyclerOptions.Builder<ExerciseCardioModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("CardioExercises"),ExerciseCardioModel.class)
                .build();

        exerciseCardioAdapter = new ExerciseCardioAdapter(options);
        recyclerView.setAdapter(exerciseCardioAdapter);

        addCardioButton = (Button)findViewById(R.id.addCardioButton);

        addCardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ExerciseCardioAdd.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        exerciseCardioAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        exerciseCardioAdapter.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                textSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                textSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void textSearch(String str)
    {
        FirebaseRecyclerOptions<ExerciseCardioModel> options =
                new FirebaseRecyclerOptions.Builder<ExerciseCardioModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CardioExercises").orderByChild("Exercise_name").startAt(str).endAt(str+"~"),ExerciseCardioModel.class)
                        .build();

        exerciseCardioAdapter = new ExerciseCardioAdapter(options);
        exerciseCardioAdapter.startListening();
        recyclerView.setAdapter(exerciseCardioAdapter);
    }

}