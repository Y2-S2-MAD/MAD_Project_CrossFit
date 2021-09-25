package com.example.crossfitappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHome extends AppCompatActivity{

    public CardView card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        card1 = (CardView) findViewById(R.id.package_card);


//        card1.setOnClickListener(this);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminCategory();
            }
        });

    }

    private void adminCategory() {
        Intent intent = new Intent(this,AdminCategory.class);
        startActivity(intent);
    }


}