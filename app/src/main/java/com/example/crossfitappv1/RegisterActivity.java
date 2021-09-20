package com.example.crossfitappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText fullname,email,password,dob,CurWeight,GoalWeight,height;
    Button signup;
    TextView Login;
    RadioButton isMale,isFemale;
    boolean valid;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullname = findViewById(R.id.editTxtRegFullName);
        email = findViewById(R.id.editTxtEmail);
        password = findViewById(R.id.editTxtPassword);
        dob = findViewById(R.id.editTxtDOB);
        CurWeight = findViewById(R.id.etCWeight);
        GoalWeight = findViewById(R.id.etGWeight);
        height = findViewById(R.id.etHeight);
        isMale = findViewById(R.id.radioBtnMale);
        isFemale = findViewById(R.id.radioBtnFemale);
        signup = findViewById(R.id.btnSignUp);
        Login = findViewById(R.id.RegToLogin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(fullname);
                checkField(email);
                checkField(password);
                checkField(dob);
                checkField(CurWeight);
                checkField(GoalWeight);
                checkField(height);

                //radiobutton validation
                if( ! (isMale.isChecked() || isFemale.isChecked())){
                    Toast.makeText(RegisterActivity.this,"Select the geneder",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (valid){

                    //User Registration process
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this,"Account Created",Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Users").document(user.getUid());
                            Map<String,Object> userInfo = new HashMap<>();
                            userInfo.put("FullName",fullname.getText().toString());
                            userInfo.put("Email",email.getText().toString());
                            userInfo.put("dob",dob.getText().toString());
                            userInfo.put("CurWeight",CurWeight.getText().toString());
                            userInfo.put("GoalWeight",GoalWeight.getText().toString());
                            userInfo.put("height",height.getText().toString());
//
                            if (isMale.isChecked()){
                                userInfo.put("isMale","1");
                            }
                            if (isFemale.isChecked()){
                                userInfo.put("isFemale","1");
                            }

                            //specify if the user is admin
                            userInfo.put("isUser","1");

                            df.set(userInfo);

                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this,"Failed to Create",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }


    private boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else
        {
            valid = true;
        }
        return valid;
    }

}