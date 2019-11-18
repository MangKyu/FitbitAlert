package com.fitbitalert.View.Scan;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.fitbitalert.BR;
import com.fitbitalert.R;
import com.fitbitalert.Utils.Exception.ScanExceptionHandler;
import com.fitbitalert.Utils.Permission.LocationPermission;
import com.fitbitalert.View.Base.BaseActivity;
import com.fitbitalert.databinding.ActivityScanBinding;
import com.polidea.rxandroidble2.exceptions.BleScanException;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import lombok.Getter;

@Getter
public class ScanActivity extends BaseActivity<ActivityScanBinding, ScanViewModel> implements ScanNavigator {

    private ActivityScanBinding binding;
    @Inject
    ScanViewModel viewModel;
    @Inject
    ScanAdapter scanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = super.getBinding();
        viewModel.setNavigator(this);
        initView();
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    public void initView() {
        initDeviceListView();
    }

    @Override
    public void onScanFailure(Throwable throwable) {
        if (throwable instanceof BleScanException) {
            ScanExceptionHandler.handleException(this, (BleScanException) throwable);
        }
    }

    @Override
    public void requestLocationPermission() {
        LocationPermission.requestLocationPermission(this);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        if (LocationPermission.isRequestLocationPermissionGranted(requestCode, permissions, grantResults)
                && !viewModel.getIsScanning().get()) {
            viewModel.scanBleDeviceList();
        }
    }


    private void initDeviceListView() {
        binding.scanResultRv.setHasFixedSize(true);
        binding.scanResultRv.setItemAnimator(null);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        binding.scanResultRv.setLayoutManager(recyclerLayoutManager);
        binding.scanResultRv.setAdapter(scanAdapter);
    }

}
