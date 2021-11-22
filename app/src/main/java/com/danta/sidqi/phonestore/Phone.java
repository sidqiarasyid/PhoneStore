package com.danta.sidqi.phonestore;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Phone extends Fragment{
    CardView btnapple, btnvivo, btnsamsung, btnxiaomi, btnoppo, btnhuawei;
    ReqAdapter adapter;
    ProgressDialog phoneDialog;




    public Phone() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_phone, container, false );
        btnapple = view.findViewById(R.id.btnapple);
        btnhuawei = view.findViewById(R.id.btnhuawer);
        btnoppo = view.findViewById(R.id.btnoppo);
        btnsamsung = view.findViewById(R.id.btnsamsung);
        btnxiaomi = view.findViewById(R.id.btnxiaomi);
        btnvivo = view.findViewById(R.id.btnvivo);
        Intent pass = new Intent(getContext(), Brand.class);




        btnapple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.putExtra("val", 1);
                startActivity(pass);
            }
        });
        btnvivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.putExtra("val", 2);
                startActivity(pass);
            }
        });
        btnxiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.putExtra("val", 3);
                startActivity(pass);
            }
        });
        btnsamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.putExtra("val", 4);
                startActivity(pass);
            }
        });
        btnoppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.putExtra("val", 5);
                startActivity(pass);
            }
        });
        btnhuawei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.putExtra("val", 6);
                startActivity(pass);
            }
        });
        return view;
    }

    }



