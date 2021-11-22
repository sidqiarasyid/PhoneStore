package com.danta.sidqi.phonestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Brand extends AppCompatActivity {
    Bundle bundle;
    TextView txtbrand;
    RecyclerView brands;
    ProgressDialog brandDialog;
    ArrayList<Model> brandList;
    ReqAdapter reqAdapter;
    String appleurl = "https://api-mobilespecs.azharimm.site/v2/brands/apple-phones-48?page=1";
    String vivourl = "https://api-mobilespecs.azharimm.site/v2/brands/vivo-phones-98?page=1";
    String xiaomi = "https://api-mobilespecs.azharimm.site/v2/brands/xiaomi-phones-80?page=1";
    String samsung = "https://api-mobilespecs.azharimm.site/v2/brands/samsung-phones-9?page=1\n";
    String oppo = "https://api-mobilespecs.azharimm.site/v2/brands/oppo-phones-82?page=1\n";
    String huawei = "https://api-mobilespecs.azharimm.site/v2/brands/huawei-phones-58?page=1\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        brandDialog = new ProgressDialog(Brand.this);
        brandDialog.setTitle("Mohon Tunggu");
        brandDialog.setCancelable(false);
        brandDialog.setMessage("Sedang menampilkan data");



        bundle = getIntent().getExtras();
        int brndid = bundle.getInt("val");
        brandList = new ArrayList<>();
        txtbrand = findViewById(R.id.txtbrand);
        brands = findViewById(R.id.rvbrand);

        switch (brndid){
            case 1:
                txtbrand.setText("Apple phones");
                getBrand(appleurl);
                break;
            case 2:
                txtbrand.setText("Vivo phones");
                getBrand(vivourl);
                break;
            case 3:
                txtbrand.setText("Xiaomi phones");
                getBrand(xiaomi);
                break;
            case 4:
                txtbrand.setText("Samsung phones");
                getBrand(samsung);
                break;
            case 5:
                txtbrand.setText("Oppo phones");
                getBrand(oppo);
                break;
            case 6:
                txtbrand.setText("Huawei phones");
                getBrand(huawei);
                break;
        }


    }

    void getBrand(String url){
        brandDialog.show();
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            brandDialog.dismiss();
                            JSONObject data = response.getJSONObject("data");
                            JSONArray brand_array = data.getJSONArray("phones");
                            for (int i = 0; i < 35; i++){
                                JSONObject brand_object = brand_array.getJSONObject(i);
                                String phone_name = brand_object.getString("phone_name");
                                String image = brand_object.getString("image");
                                String detail = brand_object.getString("detail");
                                brandList.add(new Model(phone_name, image, detail));
                            }
                            reqAdapter = new ReqAdapter(brandList);
                            RecyclerView.LayoutManager brand_grid = new GridLayoutManager(Brand.this, 2);
                            brands.setLayoutManager(brand_grid);
                            brands.setAdapter(reqAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText = newText.toString();
                ArrayList<Model> itemFilter = new ArrayList<>();
                for (Model model : brandList){
                    String nama = model.getPhoneName().toString();
                    if (nama.contains(newText)){
                        itemFilter.add(model);
                    }
                }
                reqAdapter.setFilter(itemFilter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}