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

public class ScanAdapter extends RecyclerView.Adapter<ScanAdapter.DeviceViewHolder> {

    private static final Comparator<ScanResult> SORTING_COMPARATOR = (lhs, rhs) ->
            lhs.getBleDevice().getMacAddress().compareTo(rhs.getBleDevice().getMacAddress());
    private final List<ScanResult> deviceList = new ArrayList<>();
    private OnAdapterItemClickListener onAdapterItemClickListener;

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onAdapterItemClickListener != null) {
                onAdapterItemClickListener.onAdapterViewClick(v);
            }
        }
    };

    void addScanResult(ScanResult bleScanResult) {
        for (int i = 0; i < deviceList.size(); i++) {
            if (deviceList.get(i).getBleDevice().equals(bleScanResult.getBleDevice())) {
                deviceList.set(i, bleScanResult);
                notifyItemChanged(i);
                return;
            }
        }

        deviceList.add(bleScanResult);
        Collections.sort(deviceList, SORTING_COMPARATOR);
        notifyDataSetChanged();
    }

    public void clearItems() {
        deviceList.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<ScanResult> deviceList){
        this.deviceList.addAll(deviceList);
        Collections.sort(this.deviceList, SORTING_COMPARATOR);
        notifyDataSetChanged();
    }

    ScanResult getItemAtPosition(int childAdapterPosition) {
        return deviceList.get(childAdapterPosition);
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        holder.bind(deviceList.get(position));
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    @NonNull
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDeviceBinding binding = ItemDeviceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DeviceViewHolder(binding);
    }

    void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    interface OnAdapterItemClickListener {
        void onAdapterViewClick(View view);
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
