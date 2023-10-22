package edu.abcd.smartphone.data_source.remote.response;

import com.google.gson.annotations.SerializedName;

public class CategoryRespose {
    @SerializedName("id")
    public int id;
    @SerializedName("image")
    public String image;
    @SerializedName("description")
    public String description;
    @SerializedName("enable")
    public int enable;
    @SerializedName("category_name")
    public String category_name;
    @SerializedName("parent_id")
    public String parent_id;

    public CategoryRespose(int id, String image, String description, int enable, String category_name, String parent_id) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.enable = enable;
        this.category_name = category_name;
        this.parent_id = parent_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
