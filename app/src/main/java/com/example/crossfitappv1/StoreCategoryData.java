package com.example.crossfitappv1;

public class StoreCategoryData {

    private String name,price,details,image,key;

    public StoreCategoryData() {
    }

    public StoreCategoryData(String name, String price, String details, String image, String key) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.image = image;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
