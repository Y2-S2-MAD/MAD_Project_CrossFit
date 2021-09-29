package com.example.crossfitappv1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateStoreActivity extends AppCompatActivity {

    private ImageView updateProductImage;
    private EditText txtName,txtPrice,txtDetails;
    private Button updateProductBtn,deleteProductBtn;

    private String name,price,details,image;
    private final int REQ=1;
    private Bitmap bitmap = null;
    private StorageReference storageReference;
    private DatabaseReference reference;

    private String downloadUrl, storeCategory, uniqueKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_store);

        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");
        details = getIntent().getStringExtra("details");
        image = getIntent().getStringExtra("image");

         uniqueKey = getIntent().getStringExtra("key");
        storeCategory = getIntent().getStringExtra("StoreCategory");

        updateProductImage = findViewById(R.id.updateProductImage);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDetails = findViewById(R.id.txtDetails);
        updateProductBtn = findViewById(R.id.updateProductBtn);
        deleteProductBtn = findViewById(R.id.deleteProductBtn);

        reference = FirebaseDatabase.getInstance().getReference().child("StoreCategory");
        storageReference = FirebaseStorage.getInstance().getReference();

        try {
            Picasso.get().load(image).into(updateProductImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtName.setText(name);
        txtPrice.setText(price);
        txtDetails.setText(details);

        updateProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        updateProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtName.getText().toString();
                price = txtPrice.getText().toString();
                details = txtDetails.getText().toString();

                checkValidation();
            }
        });

        deleteProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

    }

    private void deleteData() {
        reference.child(storeCategory).child(uniqueKey).removeValue()
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateStoreActivity.this,"Product deleted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateStoreActivity.this,AdminStoreCategory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateStoreActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkValidation() {
        if(name.isEmpty()){
            txtName.setError("Empty");
            txtName.requestFocus();
        }else if(price.isEmpty()){
            txtPrice.setError("Empty");
            txtPrice.requestFocus();
        }else if(details.isEmpty()){
            txtDetails.setError("Empty");
            txtDetails.requestFocus();
        }else if (bitmap == null) {
            updateData(image);
        }else{
            uploadImage();
        }
    }

    private void updateData(String s) {
        HashMap hp = new HashMap();
        hp.put("name",name);
        hp.put("price",price);
        hp.put("details",details);
        hp.put("image",s);


        reference.child(storeCategory).child(uniqueKey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
        public void onSuccess(Object o) {
                Toast.makeText(UpdateStoreActivity.this,"Store updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateStoreActivity.this,AdminStoreCategory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
    }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateStoreActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage(){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("StoreCategory").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UpdateStoreActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                                    updateData(downloadUrl);
                                }
                            });
                        }
                    });
                } else {
                    //pd.dismiss();
                    Toast.makeText(UpdateStoreActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
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
            updateProductImage.setImageBitmap(bitmap);
        }
    }
}