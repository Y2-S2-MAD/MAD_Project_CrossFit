package com.example.crossfitappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userCategory extends AppCompatActivity {


    private RecyclerView cardio,weight,strength,crossfit;

    private LinearLayout CardioNoData,WeightNoData,StrengthNoData,CrossFitNoData;
    private List<categoryData> list1,list2,list3,list4;

    private UserPackageAdapter adapter;

    private DatabaseReference reference, dbRef;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_category);

        cardio = findViewById(R.id.CardioPackage);
        weight = findViewById(R.id.weightPackage);
        strength = findViewById(R.id.strengthPackage);
        crossfit = findViewById(R.id.crossFitPackage);

        CardioNoData = findViewById(R.id.CardioNoData);
        WeightNoData = findViewById(R.id.WeightNoData);
        StrengthNoData = findViewById(R.id.StrengthNoData);
        CrossFitNoData = findViewById(R.id.CrossFitNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("category");

        cardio();
        weight();
        strength();
        crossfit();




    }

    private void cardio() {
        dbRef = reference.child("Cardio Packages");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    CardioNoData.setVisibility(View.VISIBLE);
                    cardio.setVisibility(View.GONE);
                }else {
                    CardioNoData.setVisibility(View.GONE);
                    cardio.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        categoryData data = snapshot.getValue(categoryData.class);
                        list1.add(data);
                    }
                    cardio.setHasFixedSize(true);
                    cardio.setLayoutManager(new LinearLayoutManager(userCategory.this));
                    adapter = new UserPackageAdapter(list1,userCategory.this,"Cardio Packages");
                    cardio.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(userCategory.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void weight() {
        dbRef = reference.child("Weight Packages");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    WeightNoData.setVisibility(View.VISIBLE);
                    weight.setVisibility(View.GONE);
                }else {

                    WeightNoData.setVisibility(View.GONE);
                    weight.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        categoryData data = snapshot.getValue(categoryData.class);
                        list2.add(data);
                    }
                    weight.setHasFixedSize(true);
                    weight.setLayoutManager(new LinearLayoutManager(userCategory.this));
                    adapter = new UserPackageAdapter(list2,userCategory.this,"Weight Packages");
                    weight.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(userCategory.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void strength() {
        dbRef = reference.child("Strength Packages");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    StrengthNoData.setVisibility(View.VISIBLE);
                    strength.setVisibility(View.GONE);
                }else {

                    StrengthNoData.setVisibility(View.GONE);
                    strength.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        categoryData data = snapshot.getValue(categoryData.class);
                        list3.add(data);
                    }
                    strength.setHasFixedSize(true);
                    strength.setLayoutManager(new LinearLayoutManager(userCategory.this));
                    adapter = new UserPackageAdapter(list3,userCategory.this,"Strength Packages");
                    strength.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(userCategory.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void crossfit() {
        dbRef = reference.child("Cross-Fit Packages");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    CrossFitNoData.setVisibility(View.VISIBLE);
                    crossfit.setVisibility(View.GONE);
                }else {

                    CrossFitNoData.setVisibility(View.GONE);
                    crossfit.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        categoryData data = snapshot.getValue(categoryData.class);
                        list4.add(data);
                    }
                    crossfit.setHasFixedSize(true);
                    crossfit.setLayoutManager(new LinearLayoutManager(userCategory.this));
                    adapter = new UserPackageAdapter(list4,userCategory.this,"Cross-Fit Packages");
                    crossfit.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(userCategory.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}