package com.danta.sidqi.phonestore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;


public class ProfileFragment extends Fragment {
    TextView txtuser, txtemail, txtphone;
    Button btnEditProfile;
    FirebaseAuth profileauth;
    FirebaseFirestore profilefirestore;
    String userID;


    public ProfileFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_profile, container, false);
       txtuser = view.findViewById(R.id.txUsername);
       txtemail = view.findViewById(R.id.txEmail);
       txtphone = view.findViewById(R.id.txPhoneNumber);
       btnEditProfile = view.findViewById(R.id.btnEditProfile);

       profileauth = FirebaseAuth.getInstance();
       profilefirestore = FirebaseFirestore.getInstance();

       userID = profileauth.getCurrentUser().getUid();
       DocumentReference documentReference = profilefirestore.collection("users").document(userID);
       documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
           @Override
           public void onSuccess(DocumentSnapshot documentSnapshot) {
               txtuser.setText(documentSnapshot.getString("valName"));
               txtemail.setText(documentSnapshot.getString("valEmail"));
               txtphone.setText(documentSnapshot.getString("valPhone"));
           }
       });
       btnEditProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getContext(), EditProfileActivity.class));
           }
       });



       return view;
    }
}