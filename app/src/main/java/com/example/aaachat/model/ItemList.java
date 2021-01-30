package com.example.aaachat.model;

public class ItemList {
    private String itemName;
    private String imageProfile;
    public ItemList() {
    }

    public ItemList(String itemName, String imageProfile) {
        this.itemName = itemName;
        this.imageProfile = imageProfile;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }
}