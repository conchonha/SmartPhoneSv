package edu.abcd.smartphone.data_source.remote.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("username")
    @Expose
    private String username;

    public LoginBody(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
