package com.example.ps.mvvm.viewmodel;

import android.content.Context;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ps.mvvm.adapter.UserAdapter;
import com.example.ps.mvvm.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends BaseObservable {

    //    MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
//    1
//    MutableLiveData<List<UserViewModel>> mutableUserViewModelList = new MutableLiveData<>();
    List<UserViewModel> userViewModelsList = new ArrayList<>();


    private String name;
    private String phone;
    private Context context;

    public UserViewModel(User user) {
        this.name = user.getName();
        this.phone = user.getPhone();
    }

    @Bindable
    public List<UserViewModel> getUserViewModelsList() {
        return userViewModelsList;
    }

    public void setUserViewModelsList(List<UserViewModel> userViewModelsList) {
        this.userViewModelsList = userViewModelsList;
        notifyPropertyChanged(BR.userViewModelsList);
    }

    //    2
//    public MutableLiveData<List<UserViewModel>> getMutableUserViewModelList() {
//        return mutableUserViewModelList;
//    }

    @BindingAdapter("bind:recyclerBinder")
    public static void getRecyclerBinder(RecyclerView recyclerView, List<UserViewModel> userViewModelList) {
//        mutableUserViewModelList.observe((LifecycleOwner) recyclerView.getContext(), userViewModels -> {
//            UserAdapter adapter = new UserAdapter(userViewModels);
//            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
//            recyclerView.setAdapter(adapter);
//        });
        UserAdapter adapter = new UserAdapter(userViewModelList);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
    }

    public UserViewModel(Context context) {
        this.context = context;

        for (int i = 0; i < 10; i++) {
            User user = new User("pourya " + i, "0933837646" + i);
            UserViewModel userViewModel = new UserViewModel(user);
            userViewModelsList.add(userViewModel);
        }
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
