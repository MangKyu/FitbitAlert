package com.fitbitalert.View.Scan;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.fitbitalert.Common.BaseApplication;
import com.fitbitalert.Utils.Permission.LocationPermission;
import com.fitbitalert.View.Base.BaseViewModel;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanResult;
import com.polidea.rxandroidble2.scan.ScanSettings;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScanViewModel extends BaseViewModel<ScanNavigator> {

    private ObservableBoolean isScanning;
    private final ObservableList<ScanResult> deviceObservableList = new ObservableArrayList<>();
    private final MutableLiveData<List<ScanResult>> deviceLiveDataList = new MutableLiveData<>();
    @Inject
    RxBleClient rxBleClient;

    @Builder
    public ScanViewModel(BaseApplication baseApplication, CompositeDisposable compositeDisposable, ObservableBoolean observableBoolean) {
        super(baseApplication, compositeDisposable);
        this.isScanning = observableBoolean;
    }

    public void onScanClick() {
        if (isScanning.get()) {
            dispose();
        } else {
            if (LocationPermission.isLocationPermissionGranted(getApplication().getApplicationContext())) {
                scanBleDeviceList();
            }
        }
        isScanning.set(!isScanning.get());
    }

    private void scanBleDeviceList() {
        getCompositeDisposable().add(
                rxBleClient.scanBleDevices(
                        new ScanSettings.Builder()
                                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                                .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
                                .build(),
                        new ScanFilter.Builder()
                                // .setDeviceAddress("B4:99:4C:34:DC:8B")
                                // add custom filters if needed
                                .build()
                ).observeOn(getNetworkHelper().getScheduler().ui())
                        .doFinally(this::dispose)
                        .subscribe(this::addDevice));
    }

    private void dispose() {
        getCompositeDisposable().dispose();
        deviceObservableList.clear();
    }

    private void addDevice(ScanResult scanResult) {
        for (int i = 0; i < deviceObservableList.size(); i++) {
            if (deviceObservableList.get(i).getBleDevice().equals(scanResult.getBleDevice())) {
                deviceObservableList.set(i, scanResult);
                return;
            }
        }

        deviceObservableList.add(scanResult);
    }

}