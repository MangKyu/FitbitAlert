package com.fitbitalert.View.Scan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitbitalert.databinding.ItemDeviceBinding;
import com.polidea.rxandroidble2.scan.ScanResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import lombok.Getter;

@Getter
public class ScanAdapter extends RecyclerView.Adapter<ScanAdapter.DeviceViewHolder> {

    private static final Comparator<ScanResult> SORTING_COMPARATOR = (lhs, rhs) ->
            lhs.getBleDevice().getMacAddress().compareTo(rhs.getBleDevice().getMacAddress());
    private final List<ScanResult> deviceList = new ArrayList<>();

    public void clearItems() {
        deviceList.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<ScanResult> deviceList){
        this.deviceList.addAll(deviceList);
        Collections.sort(this.deviceList, SORTING_COMPARATOR);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    // Override for duplicated bugs
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Override for duplicated bugs
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        holder.bind(deviceList.get(position));
    }

    @Override
    @NonNull
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDeviceBinding binding = ItemDeviceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DeviceViewHolder(binding);
    }

    static class DeviceViewHolder extends RecyclerView.ViewHolder {
        private ItemDeviceBinding binding;

        DeviceViewHolder(ItemDeviceBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ScanResult scanResult) {
            binding.text1.setText(String.format(Locale.getDefault(), "%s (%s)", scanResult.getBleDevice().getMacAddress(), scanResult.getBleDevice().getName()));
            binding.text2.setText(String.format(Locale.getDefault(), "RSSI: %d", scanResult.getRssi()));
        }

    }

}
