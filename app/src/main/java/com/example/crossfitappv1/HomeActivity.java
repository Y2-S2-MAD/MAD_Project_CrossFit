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
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    //comment
    TextView GoalCalories,TotalFoodCalo;
    CardView FoodCard,ExerciseCard;

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
        ExerciseCard = (CardView)  findViewById(R.id.exercise_card);

        FoodCard.setOnClickListener(this);
        ExerciseCard.setOnClickListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        GoalCalories = findViewById(R.id.txtGoalCalories);
        TotalFoodCalo = findViewById(R.id.txtFoodCalo);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        DatabaseReference dref,dref2;
        dref = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String GoalCaloriesStr = Objects.requireNonNull(snapshot.child("bmr").getValue()).toString();
                GoalCalories.setText(GoalCaloriesStr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //null
            }
        });
        Calendar calendar = Calendar.getInstance();
        String currDate = DateFormat.getDateInstance().format(calendar.getTime());
        dref2 = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

       /* dref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String calo1 = Objects.requireNonNull(snapshot.child("breakfast").child(currDate).child("calories").getValue()).toString();
                String calo2 = snapshot.child("lunch").child(currDate).child("calories").getValue().toString();
                String calo3 = snapshot.child("dinner").child(currDate).child("calories").getValue().toString();
                float cal1Float = Float.parseFloat(calo1);
                float cal2Float = Float.parseFloat(calo2);
                float cal3Float = Float.parseFloat(calo3);
                float calTotal = cal1Float+cal2Float+cal3Float;
                TotalFoodCalo.setText(Float.toString(calTotal));
                if (calo1.equals("")) {
                    TotalFoodCalo.setText("00");
                } else {
                    TotalFoodCalo.setText(currDate);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //null
            }
        });*/

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
                i = new Intent(this,AddFoodCount.class);
                startActivity(i);
                break;

            case R.id.exercise_card:
                i = new Intent(this,ActivityExerciseMain.class);
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

            case R.id.nav_logout:
                Intent intent3 = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent3);

        }

        return true;

    }
}