package com.danta.sidqi.phonestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Detail extends AppCompatActivity {
    Bundle bundle;
    TextView txtphoneName;
    //specs
    TextView txtrelease, txtdimension, txtos, txtstorage;
    ImageView imgphone;
    String pName, pImage, pDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        AndroidNetworking.initialize(Detail.this);

        txtphoneName = findViewById(R.id.txtdetailname);
        txtrelease = findViewById(R.id.txRelease);
        txtdimension = findViewById(R.id.txDimension);
        txtos = findViewById(R.id.txOs);
        txtstorage = findViewById(R.id.txStorage);
        imgphone = findViewById(R.id.detailPhone);
        getDetail();
        bundle = getIntent().getExtras();
        if (bundle != null){
            pName = bundle.getString("name");
            pImage= bundle.getString("image");
            pDetail = bundle.getString("detail");
        }
        txtphoneName.setText(pName);
        Picasso.get().load(pImage).into(imgphone);











    }
    void getDetail(){
        AndroidNetworking.get(pDetail).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject dataobj = response.getJSONObject("data");
                    String release_date = dataobj.getString("release_date");
                    txtrelease.setText(release_date);
                } catch (JSONException e) {
                   e.printStackTrace();
                }

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Detail.this, "Error mpus", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
