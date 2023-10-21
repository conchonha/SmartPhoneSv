package edu.abcd.smartphone.data_source.remote.response;

import com.google.gson.annotations.SerializedName;

public class LoginAccountRespose {
    @SerializedName("accessToken")
    public String accessToken;
    @SerializedName("refreshToken")
    public String refreshToken;
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("dateOfBirth")
    public String dateOfBirth;
    @SerializedName("gender")
    public String gender;
    @SerializedName("image")
    public String image;
    @SerializedName("role")
    public String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
