package com.example.ps.mvvm.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.ps.mvvm.R;
import com.example.ps.mvvm.databinding.ActivityMainBinding;
import com.example.ps.mvvm.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserViewModel userViewModel = new UserViewModel(this);
        binding.setUser(userViewModel);

        binding.btnViewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnViewTest.setText(String.valueOf(i));
                i++;
            }
        });

//        userViewModel.getMutableLiveData().observe(this, s -> {
//            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//        });

    }
}
