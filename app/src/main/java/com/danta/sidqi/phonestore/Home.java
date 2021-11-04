package com.danta.sidqi.phonestore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Home extends Fragment {
    RecyclerView rvpopular, rvforyou;
    ArrayList<Model> desclist, latestlist;
    ReqAdapter reqadapter;



    String recUrl = "https://api-mobilespecs.azharimm.site/v2/brands/samsung-phones-9?page=2";
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
        getDataApi();
        return view;
    }

    void getDataApi() {
        AndroidNetworking.get(recUrl)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                desclist = new ArrayList<>();
                try {
                    JSONArray phones = response.getJSONArray("phones");

                    for (int i = 0; i < 4; i++){
                        JSONObject phoneobject = phones.getJSONObject(i);
                        String phone_name = phoneobject.getString("phone_name");
                        String detail = phoneobject.getString("detail");
                        String image = phoneobject.getString("image");
                        desclist.add(new Model(phone_name, image, detail));
                    }
                    RecyclerView.LayoutManager layoutmanager = new GridLayoutManager(getActivity(),2);
                    rvpopular.setLayoutManager(layoutmanager);
                    rvpopular.setAdapter(reqadapter);


                } catch (JSONException e) {
                    e.printStackTrace();



                }
            }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

            }


        }