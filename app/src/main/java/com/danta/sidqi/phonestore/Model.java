package com.danta.sidqi.phonestore;

import java.io.Serializable;

import io.realm.RealmObject;

public class Model extends RealmObject implements Serializable{
    private String Brand;
    private String PhoneName;
    private String Image;

    public Model() {
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getPhoneName() {
        return PhoneName;
    }

    public void setPhoneName(String phoneName) {
        PhoneName = phoneName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
