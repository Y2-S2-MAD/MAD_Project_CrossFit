package com.example.crossfitappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class AddFoodCount extends AppCompatActivity {

    RecyclerView recyclerView;
    MyRecyclerViewAdapter2 myadapter;
    FloatingActionButton floatingActionButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_count);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ModelFoods> options =
                new FirebaseRecyclerOptions.Builder<ModelFoods>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Foods"),ModelFoods.class )
                .build();
        myadapter = new MyRecyclerViewAdapter2(options);
        recyclerView.setAdapter(myadapter);

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivityFoodMain.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myadapter.stopListening();
    }



}

