package com.example.ps.mvvm.remote.User;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.ps.mvvm.model.CreatedUser;
import com.example.ps.mvvm.model.Login;
import com.example.ps.mvvm.model.Token;
import com.example.ps.mvvm.remote.Api;
import com.example.ps.mvvm.model.User;
import com.example.ps.mvvm.remote.RetrofitBuilder;

import java.util.List;

import rx.SingleSubscriber;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserRepository {

    private MutableLiveData<List<User>> userMutableLiveData = new MutableLiveData<>();

    public UserRepository() {

    }

    public void getUsers() {

        Api api = RetrofitBuilder.getRetrofit();

        api.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        int a = 0;
                    }

                    @Override
                    public void onNext(UserList userList) {

                        userMutableLiveData.postValue(userList.getUserList());
                    }
                });

    }

    public void loginUser(Login login, Context context) {

        Api api = RetrofitBuilder.getRetrofit();


        api.postLogin(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<Token>() {
                    @Override
                    public void onSuccess(Token token) {
                        Toast.makeText(context, "login successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                });

    }

    public void createUser(String name, String job, Context context) {

        Api api = RetrofitBuilder.getRetrofit();

        api.postCreateUser(name, job)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<CreatedUser>() {
                    @Override
                    public void onSuccess(CreatedUser user) {
                            Toast.makeText(context, "user successfully create", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable error) {
                        int a = 0;
                    }
                });

    }

    public MutableLiveData<List<User>> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
