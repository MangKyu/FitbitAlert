package com.fitbitalert.View.Scan;

public interface ScanNavigator {

    void onScanFailure(Throwable throwable);

    void requestLocationPermission();
}
