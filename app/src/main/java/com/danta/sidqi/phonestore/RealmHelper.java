package com.danta.sidqi.phonestore;

import android.content.Context;

import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    private Context mContext;
    private Realm realm;
    private RealmResults<Model> model;

    public RealmHelper(Context mContext) {
        this.mContext = mContext;
        Realm.init(mContext);
        realm = Realm.getDefaultInstance();
    }

    public ArrayList<Model> showPhones() {
        ArrayList<Model> data = new ArrayList<>();
        model = realm.where(Model.class).findAll();

        if (model.size() > 0) {
            for (int i = 0; i < model.size(); i++) {
                Model phone = new Model();
                phone.setBrand(model.get(i).getBrand());
                phone.setImage(model.get(i).getImage());
                phone.setPhoneName(model.get(i).getPhoneName());
                data.add(phone);
            }
        }
        return data;
    }


    public void addToCart(String Brand, String PhoneName, String Image) {
        Model phone = new Model();
        phone.setBrand(Brand);
        phone.setPhoneName(PhoneName);
        phone.setImage(Image);

        realm.beginTransaction();
        realm.copyToRealm(model);
        realm.commitTransaction();
    }


    public void deleteFromCart(int id) {
        realm.beginTransaction();
        RealmResults<Model> modelMovie = realm.where(Model.class).findAll();
        modelMovie.deleteAllFromRealm();
        realm.commitTransaction();

    }

}
