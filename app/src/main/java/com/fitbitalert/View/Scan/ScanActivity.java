package com.fitbitalert.View.Scan;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.fitbitalert.BR;
import com.fitbitalert.R;
import com.fitbitalert.Utils.Exception.ScanExceptionHandler;
import com.fitbitalert.View.Base.BaseActivity;
import com.fitbitalert.databinding.ActivityScanBinding;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.scan.ScanResult;

import javax.inject.Inject;

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
        binding = getBinding();
        viewModel.setNavigator(this);
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

    private void initDeviceListView() {
        binding.scanResultRv.setHasFixedSize(true);
        binding.scanResultRv.setItemAnimator(null);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        binding.scanResultRv.setLayoutManager(recyclerLayoutManager);
        binding.scanResultRv.setAdapter(scanAdapter);
        /*
        scanAdapter.setOnAdapterItemClickListener(view -> {
            final int childAdapterPosition = binding.scanResultRv.getChildAdapterPosition(view);
            final ScanResult itemAtPosition = scanAdapter.getItemAtPosition(childAdapterPosition);
            onAdapterItemClick(itemAtPosition);
        });
        */
    }

    private void onAdapterItemClick(ScanResult scanResult) {
        Toast.makeText(this, scanResult.getBleDevice().getMacAddress(), Toast.LENGTH_LONG).show();
        /*
        final String macAddress = scanResults.getBleDevice().getMacAddress();
        final Intent intent = new Intent(this, DeviceActivity.class);
        intent.putExtra(DeviceActivity.EXTRA_MAC_ADDRESS, macAddress);
        startActivity(intent);
        */
    }

}
