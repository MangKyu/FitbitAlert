package com.fitbitalert.View.Main;

import android.os.Bundle;

import com.fitbitalert.BR;
import com.fitbitalert.R;
import com.fitbitalert.View.Base.BaseActivity;
import com.fitbitalert.databinding.ActivityMainBinding;

import javax.inject.Inject;

import lombok.Getter;

@Getter
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    private ActivityMainBinding binding;
    @Inject
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBinding();
        viewModel.setNavigator(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
