package com.example.ps.mvvm.remote;

import com.example.ps.mvvm.model.Login;
import com.example.ps.mvvm.model.Token;
import com.example.ps.mvvm.remote.User.UserList;
import com.google.gson.annotations.JsonAdapter;

import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
import rx.Single;
import rx.subjects.Subject;

public interface Api {

    @GET("users?page=2")
    Observable<UserList> getUserList();

    @POST("register")
    Single<Token> postLogin(@Body Login login);


}
