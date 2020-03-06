package com.example.ps.mvvm.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ps.mvvm.adapter.UserAdapter;
import com.example.ps.mvvm.model.Login;
import com.example.ps.mvvm.model.User;
import com.example.ps.mvvm.remote.User.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends BaseObservable {

    //    MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

    MutableLiveData<List<UserViewModel>> mutableUserViewModelList = new MutableLiveData<>();
    List<UserViewModel> userViewModelsList = new ArrayList<>();
    UserAdapter adapter;


    private String name;
    private String phone;
    private Context context;

    public UserViewModel(User user) {
        this.name = user.getName();
        this.phone = user.getPhone();
    }

    public UserAdapter getAdapter() {
        return adapter;
    }

    public void login(){

        UserRepository repository = new UserRepository();
        Login login = new Login();
        login.setEmail("eve.holt@reqres.in");
        login.setPassword("pistol");
        repository.loginUser(login);
    }

    @Bindable
    public List<UserViewModel> getUserViewModelsList() {
        return userViewModelsList;
    }

    public void setUserViewModelsList(List<UserViewModel> userViewModelsList) {
        this.userViewModelsList = userViewModelsList;
        notifyPropertyChanged(BR.userViewModelsList);
    }


    public MutableLiveData<List<UserViewModel>> getMutableUserViewModelList() {
        return mutableUserViewModelList;
    }

    @BindingAdapter("bind:recyclerBinder")
    public static void getRecyclerBinder(RecyclerView recyclerView, MutableLiveData<List<UserViewModel>> userViewModelList) {

        List<UserViewModel> userViewModelList1 = new ArrayList<>();

            UserAdapter adapter = new UserAdapter(userViewModelList1);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);

        userViewModelList.observe((LifecycleOwner) recyclerView.getContext(), userViewModellist -> {
            userViewModelList1.clear();
            userViewModelList1.addAll(userViewModellist);
            adapter.notifyDataSetChanged();
        });

    }

    public UserViewModel(Context context) {
        this.context = context;

        login();

        UserRepository repository = new UserRepository();
        repository.getUsers();
        repository.getUsers();
        repository.getUserMutableLiveData().observe((LifecycleOwner)context, user -> {
            for (int i = 0; i < user.size(); i++) {
                UserViewModel userViewModel = new UserViewModel(user.get(i));
                userViewModelsList.add(userViewModel);
            }
            mutableUserViewModelList.postValue(userViewModelsList);
        });


//        for (int i = 0; i < 10; i++) {
//            User user = new User("pourya " + i, "0933837646" + i);
//            UserViewModel userViewModel = new UserViewModel(user);
//            userViewModelsList.add(userViewModel);
//        }
//     3
//        mutableUserViewModelList.postValue(userViewModelsList);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
//        mutableLiveData.postValue(name);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public MutableLiveData<String> getMutableLiveData() {
//        return mutableLiveData;
//    }
}