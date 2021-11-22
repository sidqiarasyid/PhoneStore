package com.danta.sidqi.phonestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    EditText edtusername, edtemail, edtphone;
    Button btnedtprof;
    FirebaseAuth edtAuth;
    FirebaseFirestore edtFirestore;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        edtusername = findViewById(R.id.txChangeUsername);
        edtemail = findViewById(R.id.txChangeEmail);
        edtphone = findViewById(R.id.txChangePhoneNumber);
        btnedtprof = findViewById(R.id.btnSave);
        edtAuth = FirebaseAuth.getInstance();
        user = edtAuth.getCurrentUser();
        edtFirestore = FirebaseFirestore.getInstance();





        btnedtprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment profileFragment = new ProfileFragment();
                String email = edtemail.getText().toString();
                String phone = edtphone.getText().toString();
                String username = edtusername.getText().toString();
                DocumentReference reference = edtFirestore.collection("users").document(user.getUid());
                Map<String,Object> edited = new HashMap<>();
                edited.put("valEmail", email);
                edited.put("valPhone", phone);
                edited.put("valName", username);
                reference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                    }
                });
                user.updateEmail(email);


            }
        });

    }

}