package edu.abcd.smartphone.data_source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductRespose implements Serializable {
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public double price;
    @SerializedName("description")
    public String description;
    @SerializedName("image")
    public String image;
    @SerializedName("quantity")
    public int quantity;
    @SerializedName("enable")
    public boolean enable;
    @SerializedName("categories")
    public CategoryRespose categories;
    private double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
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

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public CategoryRespose getCategories() {
        return categories;
    }

    public void setCategories(CategoryRespose categories) {
        this.categories = categories;
    }
}
