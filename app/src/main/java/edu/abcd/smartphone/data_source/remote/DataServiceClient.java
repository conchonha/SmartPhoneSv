package edu.abcd.smartphone.data_source.remote;

import java.util.List;

import edu.abcd.smartphone.data_source.remote.body.LoginBody;
import edu.abcd.smartphone.data_source.remote.body.RegisterBody;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.data_source.remote.response.RegisterAccountResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataServiceClient {
//    @FormUrlEncoded
//    @POST("/auth/register")
//    Call<RegisterAccountResponse> register(@Field("email") String email, @Field("name") String name, @Field("password") String password);

    @POST("auth/register")
    Call<RegisterAccountResponse> register(@Body RegisterBody registerBody);

    @POST("auth/login")
    Call<LoginAccountRespose> login(@Body LoginBody loginBody);
}