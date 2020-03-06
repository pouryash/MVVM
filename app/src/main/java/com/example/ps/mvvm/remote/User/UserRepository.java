package com.example.ps.mvvm.remote.User;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

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

    public void loginUser(Login login) {

        Api api = RetrofitBuilder.getRetrofit();


        api.postLogin(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<Token>() {
                    @Override
                    public void onSuccess(Token token) {
                        int a = 0;
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
