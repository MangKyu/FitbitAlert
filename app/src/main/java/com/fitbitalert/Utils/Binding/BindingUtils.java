package com.fitbitalert.Utils.Binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.fitbitalert.View.Scan.ScanAdapter;
import com.polidea.rxandroidble2.scan.ScanResult;

import java.util.List;

public final class BindingUtils {

    @BindingAdapter({"scan_adapter"})
    public static void addDeviceList(RecyclerView recyclerView, List<ScanResult> deviceList) {
        ScanAdapter scanAdapter = (ScanAdapter) recyclerView.getAdapter();
        if (scanAdapter != null) {
            scanAdapter.clearItems();
            scanAdapter.addItems(deviceList);
        }
    }

}
