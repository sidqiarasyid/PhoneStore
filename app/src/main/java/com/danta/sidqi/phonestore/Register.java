package com.danta.sidqi.phonestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText username, email, password, phone;
    Button btnRegister;
    FirebaseAuth registerAuth;
    FirebaseFirestore firestore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (registerAuth != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }



        username = findViewById(R.id.insUsername);
        email = findViewById(R.id.insEmail);
        password = findViewById(R.id.insPassword);
        phone = findViewById(R.id.idPhone);
        btnRegister = findViewById(R.id.btnRegister);
        registerAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

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
                String emailval = email.getText().toString();
                String passwordval = password.getText().toString();
                String phoneNum = phone.getText().toString();
                String usernameval = username.getText().toString();

                if (TextUtils.isEmpty(emailval)){
                    email.setError("Email is Required");
                }
                if (TextUtils.isEmpty(passwordval)){
                    password.setError("Password is required");
                }


                registerAuth.createUserWithEmailAndPassword(emailval, passwordval).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "Register Complete", Toast.LENGTH_SHORT).show();
                            userID = registerAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("user").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("valName", usernameval);
                            user.put("valPhone", phoneNum);
                            user.put("valEmail", emailval);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            });

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, "Register Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}