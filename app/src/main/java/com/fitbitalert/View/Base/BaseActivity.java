package com.fitbitalert.View.Base;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fitbitalert.Network.Helper.NetworkHelper;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import lombok.Getter;

@Getter
public abstract class BaseActivity<B extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    private B binding;
    private V viewModel;
    @Inject
    NetworkHelper networkHelper;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract @LayoutRes int getLayoutId();

    public abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
        initBaseActivity();
    }

    private void initBaseActivity() {
        this.viewModel.setNetworkHelper(networkHelper);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    private void performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        this.viewModel = viewModel != null ? viewModel : getViewModel();
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
    }

}
