package com.example.crossfitappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
public class PackagePayment extends AppCompatActivity {

    private EditText TotalAmount,pkgName, HolderName, cardNum, eDate, cvn;
    private Button cancel;
    private String amount,pname;
    Button button2;
    Dialog dialog;
    EditText dateTXT;
    ImageView cal;
    private int pDate, pMonth, pYear;

    DatabaseReference PackagePaymentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_payment);



        amount = getIntent().getStringExtra("expireDate");
        pname = getIntent().getStringExtra("name");

        pkgName = findViewById(R.id.etPname);
        TotalAmount = findViewById(R.id.etTAmount);
        cancel = findViewById(R.id.btnCancel);
        HolderName = findViewById(R.id.etCardHolderName);
        cardNum = findViewById(R.id.etCardNum);
        eDate = findViewById(R.id.editTextDate);
        cvn = findViewById(R.id.etCVN);
        button2 = findViewById(R.id.button2);

        dateTXT = findViewById(R.id.editTextDate);
        cal = findViewById(R.id.calender);

        //set the name and amount in payment page
        TotalAmount.setText(amount);
        pkgName.setText(pname);

        PackagePaymentRef = FirebaseDatabase.getInstance().getReference().child("packagepayment");



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar Cal = Calendar.getInstance();
                pDate = Cal.get(Calendar.DATE);
                pMonth = Cal.get(Calendar.MONTH);
                pYear = Cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(PackagePayment.this, android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        dateTXT.setText(date+"-"+month+"-"+year);
                    }
                }, pYear, pMonth, pDate);
                datePickerDialog.show();
            }
        });
        button2 =findViewById(R.id.button2);
        dialog = new Dialog(this);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String holderName = HolderName.getText().toString();
                String CardNum = cardNum.getText().toString();
                String Expiredate = eDate.getText().toString();
                String CVN = cvn.getText().toString();
                String pname = pkgName.getText().toString();
                String amount = TotalAmount.getText().toString();


                PPayment pp = new PPayment(pname,amount,holderName,CardNum,Expiredate,CVN);
                PackagePaymentRef.push().setValue(pp);

                if (holderName.isEmpty()){
                    Toast.makeText(PackagePayment.this, "Please enter Card holder name!", Toast.LENGTH_SHORT).show();
                }
                else if (CardNum.isEmpty()){
                    Toast.makeText(PackagePayment.this, "Please enter Card number!", Toast.LENGTH_SHORT).show();
                }
                else if (Expiredate.isEmpty()){
                    Toast.makeText(PackagePayment.this, "Please enter Expire date!", Toast.LENGTH_SHORT).show();
                }
                else if (CVN.isEmpty() || cvn.length() != 3 ) {
                    Toast.makeText(PackagePayment.this, "Enter valid cvn number", Toast.LENGTH_SHORT).show();
                }
                else if (holderName.isEmpty() || CardNum.isEmpty() || Expiredate.isEmpty() || CVN.isEmpty()) {
                    Toast.makeText(PackagePayment.this, "All fields are required!!", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(PackagePayment.this, "Successfully!!", Toast.LENGTH_SHORT).show();
                    openWingDialog();
                }


            }

        });

    }

    private  void  openWingDialog(){
        dialog.setContentView(R.layout.activity_popup_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView8= dialog.findViewById(R.id.imageView8);
        Button BtnOk = dialog.findViewById(R.id.button1);

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        BtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}