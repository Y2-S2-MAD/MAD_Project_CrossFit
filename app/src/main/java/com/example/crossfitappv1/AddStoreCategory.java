package com.example.crossfitappv1;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddStoreCategory extends AppCompatActivity {

    private ImageView imageView5;
    private EditText txtName,txtPrice,txtDetails;
    private Spinner storeCategory;
    private Button btnAdd;
    private final int REQ=1;
    private Bitmap bitmap = null;
    private String categoryList;
    private String name,price,details, downloadUrl ="";
    private ProgressDialog pd;

    private StorageReference storageReference;
    private DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store_category);

        imageView5 = findViewById(R.id.imageView5);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDetails = findViewById(R.id.txtDetails);
        storeCategory = findViewById(R.id.storeCategory);
        btnAdd = findViewById(R.id.btnAdd);

        pd = new ProgressDialog(this);

        reference = FirebaseDatabase.getInstance().getReference().child("StoreCategory");
        storageReference = FirebaseStorage.getInstance().getReference();

        String[] items = new String[]{"Select Store_Category", "Protein", "Fitness Accessories", "Creatine", "Fat Burns"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,)
        storeCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));
        storeCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                categoryList = storeCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imageView5.setOnClickListener( (view)->{
            openGallery();
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });
    }
    private void checkValidation(){
        name = txtName.getText().toString();
        price = txtPrice.getText().toString();
        details = txtDetails.getText().toString();


        if (name.isEmpty()){
            txtName.setError("Empty");
            txtName.requestFocus();
        }else  if (price.isEmpty()){
            txtPrice.setError("Empty");
            txtPrice.requestFocus();
        }else  if (details.isEmpty()){
            txtDetails.setError("Empty");
            txtDetails.requestFocus();
        }else if(categoryList.equals("Select Store_Category")){
            Toast.makeText(this,"Please provide category",Toast.LENGTH_SHORT).show();
        }else if(bitmap == null ){
            pd.setMessage("Uploading...");
            pd.show();
            insertData();
        }else{
            pd.setMessage("Uploading...");
            pd.show();
            uploadImage();
        }
    }

    private void uploadImage(){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("StoreCategory").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(AddStoreCategory.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    insertData();
                                }
                            });
                        }
                    });
                } else {
                    pd.dismiss();
                    Toast.makeText(AddStoreCategory.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void insertData(){
        dbRef = reference.child(categoryList);
        final String uniqueKey = dbRef.push().getKey();
        StoreCategoryData storecategorydata = new StoreCategoryData(name,price,details,downloadUrl,uniqueKey);

        dbRef.child(uniqueKey).setValue(storecategorydata).addOnSuccessListener((OnSuccessListener) (aVoid)-> {
            pd.dismiss();
            Toast.makeText(AddStoreCategory.this, "Store category Added", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(AddStoreCategory.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
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