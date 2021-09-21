package com.example.crossfitappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class ActivityFoodMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);


        //Bottom navigation
        BottomNavigationView btnNav = findViewById(R.id.bottomNAvigationView);
        btnNav.setOnItemSelectedListener(navListener);

        //Setting Home fragment as main fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout,new FragmentAnalize()).commit();

    }

    //Listener nav bar
    private NavigationBarView.OnItemSelectedListener navListener = new BottomNavigationView.OnItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.item1:
                    selectedFragment = new FragmentAnalize();
                    break;
                case R.id.item2:
                    selectedFragment = new FragmentAdd();
                    break;
                case R.id.item3:
                    selectedFragment = new FragmentList();
                    break;
            }

            //begin transaction
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout
                    ,selectedFragment).commit();
            return true;
        }
    };
}