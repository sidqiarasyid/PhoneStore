package com.danta.sidqi.phonestore;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Phone extends Fragment{

    ProgressDialog listProgressDialog;
    RecyclerView rvPhoneList;
    ArrayList<Model> phonelist;
    ReqAdapter reqAdapter;
    String ListURL = "https://api-mobilespecs.azharimm.site/v2/brands/apple-phones-9?page=1";


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
        // Inflate the layout for this fragment
        listProgressDialog = new ProgressDialog(getActivity());
        listProgressDialog.setCancelable(false);
        listProgressDialog.setTitle("LODING");
        listProgressDialog.setMessage("yg sabar nggeh");
        View view = inflater.inflate(R.layout.fragment_phone, container, false );
        rvPhoneList = view.findViewById(R.id.rvlist);
        phonelist = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListPhone();
    }
    void getListPhone(){
        listProgressDialog.show();
        AndroidNetworking.get(ListURL).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    listProgressDialog.dismiss();
                    JSONObject datalist = response.getJSONObject("data");
                    JSONArray listphone = datalist.getJSONArray("phones");
                    for (int i = 0; i <= 40;i++){
                        JSONObject listobject = listphone.getJSONObject(i);
                        String phonename = listobject.getString("phone_name");
                        String image = listobject.getString("image");
                        phonelist.add(new Model(phonename ,image));
                    }
                    RecyclerView.LayoutManager gridmanager = new GridLayoutManager(getActivity(),2);
                    reqAdapter = new ReqAdapter(phonelist);
                    rvPhoneList.setLayoutManager(gridmanager);
                    rvPhoneList.setAdapter(reqAdapter);
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