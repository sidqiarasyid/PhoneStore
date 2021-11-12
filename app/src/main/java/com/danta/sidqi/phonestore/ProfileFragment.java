package com.danta.sidqi.phonestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ProfileFragment extends Fragment {
    TextView txtuser, txtemail, txtphone;
    Button btnEditProfile;


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
       return view;
    }
}