package com.example.ps.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps.mvvm.R;
import com.example.ps.mvvm.databinding.UserItemBinding;
import com.example.ps.mvvm.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter2 extends RecyclerView.Adapter<UserAdapter2.UserVH> {

    private List<UserViewModel> viewModelList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public UserAdapter2(List<UserViewModel> viewModelList) {
        this.viewModelList = viewModelList;
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        UserItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.user_row, parent, false);
        return new UserVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        viewModelList.get(position).setName(viewModelList.get(position).getName()+"1452");
        UserViewModel viewModel = viewModelList.get(position);
        holder.BindUser(viewModel);
    }

    @Override
    public int getItemCount() {
        return viewModelList.size();
    }

    class UserVH extends RecyclerView.ViewHolder {

        UserItemBinding binding;

        public UserVH(@NonNull UserItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void BindUser(UserViewModel viewModel){
            binding.setUser(viewModel);
            binding.executePendingBindings();
        }

    }
}
