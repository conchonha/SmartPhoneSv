package edu.abcd.smartphone.data_source.remote;

import java.util.List;

import edu.abcd.smartphone.data_source.remote.body.LoginBody;
import edu.abcd.smartphone.data_source.remote.body.RegisterBody;
import edu.abcd.smartphone.data_source.remote.response.CategoryRespose;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.data_source.remote.response.ProductRespose;
import edu.abcd.smartphone.data_source.remote.response.RegisterAccountResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataServiceClient {
//    @FormUrlEncoded
//    @POST("/auth/register")
//    Call<RegisterAccountResponse> register(@Field("email") String email, @Field("name") String name, @Field("password") String password);

    @POST("auth/register")
    Call<RegisterAccountResponse> register(@Body RegisterBody registerBody);

    @POST("auth/login")
    Call<LoginAccountRespose> login(@Body LoginBody loginBody);

    @GET("category")
    Call<List<CategoryRespose>> category();

    @GET("products")
    Call<List<ProductRespose>> product();

    @GET("products/getByCate")
    Call<List<ProductRespose>> productFromIdCategory(@Query("id") int id);
}