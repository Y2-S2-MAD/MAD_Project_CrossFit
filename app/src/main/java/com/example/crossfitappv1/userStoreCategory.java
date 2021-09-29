package com.example.crossfitappv1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userStoreCategory extends AppCompatActivity {


    private RecyclerView protein,fitnessAcc,creatine,fatBurns;

    private LinearLayout ProteinNoData,FitnessAccNoData,CreatineNoData,FatBurnsNoData;
    private List<StoreCategoryData> list1,list2,list3,list4;

    private UserStoreAdapter adapter;

    private DatabaseReference reference, dbRef;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_store_category);

        protein = findViewById(R.id.Protein);
        fitnessAcc = findViewById(R.id.FitnessAcc);
        creatine = findViewById(R.id.Creatine);
        fatBurns = findViewById(R.id.FatBurns);

        ProteinNoData = findViewById(R.id.ProteinNoData);
        FitnessAccNoData = findViewById(R.id.FitnessAccNoData);
        CreatineNoData = findViewById(R.id.CreatineNoData);
        FatBurnsNoData = findViewById(R.id.FatBurnsNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("StoreCategory");

        protein();
        fitnessAcc();
        creatine();
        fatBurns();




    }

    private void protein() {
        dbRef = reference.child("Protein");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    ProteinNoData.setVisibility(View.VISIBLE);
                    protein.setVisibility(View.GONE);
                }else {
                    ProteinNoData.setVisibility(View.GONE);
                    protein.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StoreCategoryData data = snapshot.getValue(StoreCategoryData.class);
                        list1.add(data);
                    }
                    protein.setHasFixedSize(true);
                    protein.setLayoutManager(new LinearLayoutManager(userStoreCategory.this));
                    adapter = new UserStoreAdapter(list1,userStoreCategory.this,"Protein");
                    protein.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(userStoreCategory.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fitnessAcc() {
        dbRef = reference.child("Fitness Accessories");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    FitnessAccNoData.setVisibility(View.VISIBLE);
                    fitnessAcc.setVisibility(View.GONE);
                }else {

                    FitnessAccNoData.setVisibility(View.GONE);
                    fitnessAcc.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StoreCategoryData data = snapshot.getValue(StoreCategoryData.class);
                        list2.add(data);
                    }
                    fitnessAcc.setHasFixedSize(true);
                    fitnessAcc.setLayoutManager(new LinearLayoutManager(userStoreCategory.this));
                    adapter = new UserStoreAdapter(list2,userStoreCategory.this,"Fitness Accessories");
                    fitnessAcc.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(userStoreCategory.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void creatine() {
        dbRef = reference.child("Creatine");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    CreatineNoData.setVisibility(View.VISIBLE);
                    creatine.setVisibility(View.GONE);
                }else {

                    CreatineNoData.setVisibility(View.GONE);
                    creatine.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StoreCategoryData data = snapshot.getValue(StoreCategoryData.class);
                        list3.add(data);
                    }
                    creatine.setHasFixedSize(true);
                    creatine.setLayoutManager(new LinearLayoutManager(userStoreCategory.this));
                    adapter = new UserStoreAdapter(list3,userStoreCategory.this,"Creatine");
                    creatine.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(userStoreCategory.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fatBurns() {
        dbRef = reference.child("Fat Burns");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    FatBurnsNoData.setVisibility(View.VISIBLE);
                    fatBurns.setVisibility(View.GONE);
                }else {

                    FatBurnsNoData.setVisibility(View.GONE);
                    fatBurns.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StoreCategoryData data = snapshot.getValue(StoreCategoryData.class);
                        list4.add(data);
                    }
                    fatBurns.setHasFixedSize(true);
                    fatBurns.setLayoutManager(new LinearLayoutManager(userStoreCategory.this));
                    adapter = new UserStoreAdapter(list4,userStoreCategory.this,"Fat Burns");
                    fatBurns.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(userStoreCategory.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}