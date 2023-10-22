package edu.abcd.smartphone.data_source.remote.response;

import com.google.gson.annotations.SerializedName;

public class ProductRespose {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public int price;
    @SerializedName("description")
    public String description;
    @SerializedName("image")
    public String image;
    @SerializedName("quantity")
    public int quantity;
    @SerializedName("enable")
    public int enable;
    @SerializedName("categories")
    public String categories;
    private double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
