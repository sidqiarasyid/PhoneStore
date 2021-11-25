package com.danta.sidqi.phonestore;

import static io.realm.Realm.getApplicationContext;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Home extends Fragment {
    RecyclerView rvpopular, rvforyou;
    ArrayList<Model> desclist, latestlist;
    ReqAdapter reqadapter;
    LatestAdapter latestAdapter;
    ProgressDialog loding;




    String recUrl = " https://api-mobilespecs.azharimm.site/v2/brands/xiaomi-phones-80?page=1";
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

        loding = new ProgressDialog(getActivity());
        loding.setCancelable(false);
        loding.setTitle("Mohon Tunggu");
        loding.setMessage("Sedang menampilkan data");




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        desclist = new ArrayList<>();
        latestlist = new ArrayList<>();
        getDataApi();
        getLatestApi();

    }
    public void getDataApi(){
        loding.show();
        AndroidNetworking.get(recUrl)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            loding.dismiss();
                            JSONObject data = response.getJSONObject("data");
                            JSONArray phones = data.getJSONArray("phones");
                            for (int i = 0; i < 4; i++){
                                JSONObject phones_object = phones.getJSONObject(i);
                                String phone_name = phones_object.getString("phone_name");
                                String image = phones_object.getString("image");
                                String detailurl = phones_object.getString("slug");
                                desclist.add(new Model(phone_name, image, detailurl));
                            }
                            reqadapter = new ReqAdapter(desclist);
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
    public void getLatestApi(){
        AndroidNetworking.get(lateUrl)
                .setPriority(Priority.HIGH)
                .build()

                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray latePhone = data.getJSONArray("phones");
                            for (int o = 0; o < 4; o++){
                                JSONObject late_object = latePhone.getJSONObject(o);
                                String late_name = late_object.getString("phone_name");
                                String late_image = late_object.getString("image");
                                String late_detail = late_object.getString("slug");
                                latestlist.add(new Model(late_name, late_image,late_detail, false));
                            }
                            latestAdapter = new LatestAdapter(latestlist);
                            RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
                            rvforyou.setAdapter(latestAdapter);
                            rvforyou.setLayoutManager(manager);

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


