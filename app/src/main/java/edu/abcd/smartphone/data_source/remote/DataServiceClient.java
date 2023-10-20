package edu.abcd.smartphone.data_source.remote;

import edu.abcd.smartphone.data_source.remote.body.RegisterBody;
import edu.abcd.smartphone.data_source.remote.response.RegisterAccountResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataServiceClient {
    @FormUrlEncoded
    @POST("session")
    Call<RegisterAccountResponse> register(@Field("email") String email, @Field("name") String name, @Field("password") String password);

    @POST("session")
    Call<RegisterAccountResponse> register(@Body RegisterBody registerBody);
}