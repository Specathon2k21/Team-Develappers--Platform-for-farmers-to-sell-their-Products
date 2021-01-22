package com.example.aaachat.model;

import android.graphics.drawable.Drawable;

public class ItemList {
    private String itemName;
    private Drawable imageProfile;

    public ItemList() {
    }

    public ItemList(String itemName, Drawable imageProfile) {
        this.itemName = itemName;
        this.imageProfile = imageProfile;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Drawable getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(Drawable imageProfile) {
        this.imageProfile = imageProfile;
    }
}