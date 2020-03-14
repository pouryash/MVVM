package com.example.ps.mvvm.remote;

import com.example.ps.mvvm.model.CreatedUser;
import com.example.ps.mvvm.model.Login;
import com.example.ps.mvvm.model.Token;
import com.example.ps.mvvm.model.User;
import com.example.ps.mvvm.remote.User.UserList;
import com.google.gson.annotations.JsonAdapter;

import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;
import rx.Single;


public interface Api {

    @Headers({"Content-type: application/json"})
    @GET("users?page=2")
    Observable<UserList> getUserList();

    @Headers({"Content-type: application/json"})
    @POST("register")
    Single<Token> postLogin(@Body Login login);

    @Headers({"Content-type: application/json"})
    @POST("users")
    Single<CreatedUser> postCreateUser(@Query("name") String name, @Query("job") String job);


}
