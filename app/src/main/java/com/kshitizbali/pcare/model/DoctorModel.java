package com.kshitizbali.pcare.model;

import com.google.gson.annotations.SerializedName;

public class DoctorModel {


    private String name;
    private String rating;
    private String photo;
    private int exp;
    private String res_prog;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getRes_prog() {
        return res_prog;
    }

    public void setRes_prog(String res_prog) {
        this.res_prog = res_prog;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
