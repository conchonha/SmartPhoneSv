package edu.abcd.smartphone.data_source.remote.response;

import com.google.gson.annotations.SerializedName;

public class RegisterAccountResponse {
    @SerializedName("id")
    public int id;
    @SerializedName("email")
    public String email;
    @SerializedName("role")
    public String role;

    public RegisterAccountResponse(int id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
