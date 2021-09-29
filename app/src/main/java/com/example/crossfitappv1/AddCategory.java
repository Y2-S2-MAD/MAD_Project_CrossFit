package com.example.crossfitappv1;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddCategory extends AppCompatActivity {


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.IOException;

public class AddCategory extends AppCompatActivity {

    private ImageView imageView5;
    private EditText txtName,txtDes,txtAvailableDate,txtExpireDate;
    private Spinner category;
    private Button btnAdd;
    private final int REQ=1;
    private Bitmap bitmap = null;
    private String categoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);


        imageView5 = findViewById(R.id.imageView5);
        txtName = findViewById(R.id.txtName);
        txtDes = findViewById(R.id.txtDes);
        txtAvailableDate = findViewById(R.id.txtAvailableDate);
        txtExpireDate = findViewById(R.id.txtExpireDate);
        category = findViewById(R.id.category);
        btnAdd = findViewById(R.id.btnAdd);

        String[] items = new String[]{"Select Category", "Cardio Packages", "Weight Packages", "Strength Packages", "Cross-Fit Packages"};
        category.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item));
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                categoryList = category.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imageView5.setOnClickListener( (view)->{
            openGallery();
        });
    }
    private void openGallery(){
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQ && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

            }catch(IOException e){
                e.printStackTrace();
            }
            imageView5.setImageBitmap(bitmap);
        }

    }
}