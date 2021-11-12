package com.danta.sidqi.phonestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {
    EditText edtusername, edtemail, edtphone;
    Button btnedtprof;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        edtusername = findViewById(R.id.txChangeUsername);
        edtemail = findViewById(R.id.txChangeEmail);
        edtphone = findViewById(R.id.txChangePhoneNumber);
        btnedtprof = findViewById(R.id.btnSave);


        String email = edtemail.getText().toString();
        String phonenum = edtphone.getText().toString();





    }
}