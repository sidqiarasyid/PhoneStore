package com.danta.sidqi.phonestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Home extends Fragment {
    RecyclerView rvpopular, rvforyou;
    String recUrl = "https://api-mobilespecs.azharimm.site/v2/brands/xiaomi-phones-80?page=1";
    String lateUrl = "https://api-mobilespecs.azharimm.site/v2/latest";


    public Home() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvpopular = view.findViewById(R.id.rvpopular);
        rvforyou = view.findViewById(R.id.rvforyou);
        return view;
    }

    public void loadRecAPI(){

    }
}