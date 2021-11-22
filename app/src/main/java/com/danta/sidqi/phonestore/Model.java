package com.danta.sidqi.phonestore;

import java.io.Serializable;



public class Model {
    private String phoneName;
    private String latePhoneName;
    private String lateImageName;
    private String image;
    private String listName;
    private String listImage;
    private String lateUrl;
    private String detailUrl;
    private String releaseDate;
    private String os;
    private String storage;
    private String dimension;
    private String brand;
    private String thumbnail;
    private String searchname;
    private String searchimage;
    private String searhdetail;

    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    public String getSearchimage() {
        return searchimage;
    }

    public void setSearchimage(String searchimage) {
        this.searchimage = searchimage;
    }

    public String getSearhdetail() {
        return searhdetail;
    }

    public void setSearhdetail(String searhdetail) {
        this.searhdetail = searhdetail;
    }

    public String getLateUrl() {
        return lateUrl;
    }

    public void setLateUrl(String lateUrl) {
        this.lateUrl = lateUrl;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListImage() {
        return listImage;
    }

    public void setListImage(String listImage) {
        this.listImage = listImage;
    }

    public String getLatePhoneName() {
        return latePhoneName;
    }

    public void setLatePhoneName(String latePhoneName) {
        this.latePhoneName = latePhoneName;
    }

    public String getLateImageName() {
        return lateImageName;
    }

    public void setLateImageName(String lateImageName) {
        this.lateImageName = lateImageName;
    }

    public Model() {
    }

    public Model(String phone_name, String image, String detailUrl) {
        this.phoneName = phone_name;
        this.image = image;
        this.detailUrl = detailUrl;
    }
    public Model (String latePhoneName, String lateImageName, String lateUrl, Boolean ngisi){
        this.latePhoneName = latePhoneName;
        this.lateImageName = lateImageName;
        this.lateUrl = lateUrl;
    }
    //



    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
