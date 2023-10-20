package edu.abcd.smartphone.data_source.remote.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterBody {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;

    public RegisterBody(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
