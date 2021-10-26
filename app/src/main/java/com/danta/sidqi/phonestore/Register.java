package com.danta.sidqi.phonestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    String Username, Email, Password;
    EditText username, email, password;
    Button btnRegister;
    LoginHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        helper= new LoginHelper(this);

        username = findViewById(R.id.insUsername);
        email = findViewById(R.id.insEmail);
        password = findViewById(R.id.insPassword);
        btnRegister = findViewById(R.id.btnRegister);

        TextView btnBackToLogin = (TextView) findViewById(R.id.btnBackToLogin);

        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                Email = email.getText().toString();
                Password = password.getText().toString();

                ContentValues values =new ContentValues();

              if (Password.equals("") || Username.equals("")){
                    Toast.makeText(Register.this, "Username atau Password tidak bisa dikosongkan", Toast.LENGTH_SHORT).show();
                }else {
                    values.put(LoginHelper.rov_username, String.valueOf(Username));
                    values.put(LoginHelper.rov_password, String.valueOf(Password));
                    helper.insertData(values);

                    Toast.makeText(Register.this, "Register Sukses", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}