package com.danta.sidqi.phonestore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
    ProgressDialog load;


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
        load = new ProgressDialog(this);
        load.setCancelable(false);
        load.setTitle("Mohon Tunggu");
        load.setMessage("Sedang menampilkan Data");
        bundle = getIntent().getExtras();
        if (bundle != null){
            pName = bundle.getString("name");
            pImage= bundle.getString("image");
            pDetail = bundle.getString("detail");
        }
        getDetail();
        txtphoneName.setText(pName);
        Picasso.get().load(pImage).into(imgphone);
        AndroidNetworking.initialize(this);
        












    }
    void getDetail(){
        load.show();
        AndroidNetworking.get("https://api-mobilespecs.azharimm.site/v2/" + pDetail)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    load.dismiss();
                    JSONObject dataobj = response.getJSONObject("data");
                    String release_date = dataobj.getString("release_date");
                    String dimension = dataobj.getString("dimension");
                    String os = dataobj.getString("os");
                    String storage = dataobj.getString("storage");
                    if (release_date.equals("")){
                        txtrelease.setText("N/A");
                    } else {
                        txtrelease.setText(release_date);
                    }
                    if (dimension.equals("")){
                        txtdimension.setText("N/A");
                    } else {
                        txtdimension.setText(dimension);
                    }
                    if (os.equals("")){
                        txtos.setText("N/A");
                    } else {
                        txtos.setText(os);
                    }
                    if (storage.equals("")){
                        txtstorage.setText("N/A");
                    } else {
                        txtstorage.setText(storage);
                    }


                } catch (JSONException e) {
                   e.printStackTrace();
                }

            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(Detail.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
