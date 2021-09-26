package com.example.crossfitappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    public CardView card1;


import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    //comment
    CardView FoodCard,ExerciseCard,StoreCard;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        card1 = (CardView) findViewById(R.id.package_card);

        card1.setOnClickListener(this);

        FoodCard = (CardView) findViewById(R.id.food_card);
        ExerciseCard = (CardView)  findViewById(R.id.admin_exercise_card);
        StoreCard = (CardView) findViewById(R.id.store_card);

        FoodCard.setOnClickListener(this);
        ExerciseCard.setOnClickListener(this);
        StoreCard.setOnClickListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()) {
            case R.id.package_card:
                i = new Intent(this, AdminCategory.class);
                startActivity(i);
                break;
        }


        Intent i;

        switch (v.getId()){
            case R.id.food_card:
                i = new Intent(this,ActivityFoodMain.class);
                startActivity(i);
                break;

            case R.id.admin_exercise_card:
                i = new Intent(this,ActivityExerciseMain.class);
                startActivity(i);
                break;

            case R.id.store_card:
                i = new Intent(this,userStoreCategory.class);
                startActivity(i);
                break;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_home:
                break;

            case R.id.nav_food:
                Intent intent1 = new Intent(HomeActivity.this,ActivityFoodMain.class);
                startActivity(intent1);

            case R.id.nav_exercise:
                Intent intent2 = new Intent(HomeActivity.this,ActivityExerciseMain.class);
                startActivity(intent2);

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }
}