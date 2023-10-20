package edu.abcd.smartphone.data_source.remote.response;

import com.google.gson.annotations.SerializedName;

public class RegisterAccountResponse {
    @SerializedName("id")
    public int id;
    @SerializedName("email")
    public String email;
    @SerializedName("role")
    public String role;
}
