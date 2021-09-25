package com.example.crossfitappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ActivityExerciseMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_main);

        //bottom navigation view
        BottomNavigationView btnNav = findViewById(R.id.Exercise_bottomNavigationviwe);
        btnNav.setOnItemSelectedListener(navListener);

        //setting Exercise add Fragment as main fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout,new ExerciseAddFragment()).commit();

    }

    //Listener nav bar

    private NavigationBarView.OnItemSelectedListener navListener = new BottomNavigationView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.Exitem1:
                    selectedFragment = new ExerciseListFragment();
                    break;

                case R.id.Exitem2:
                    selectedFragment = new ExerciseAddFragment();
                    break;

                case R.id.Exitem3:
                    selectedFragment = new ExerciseVideoFragment();
                    break;
            }

            //begin transaction
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout,selectedFragment).commit();

            return true;

        }
    };
}