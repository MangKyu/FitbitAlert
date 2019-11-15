package com.fitbitalert.DI.Module;

import com.fitbitalert.View.Scan.ScanActivity;
import com.fitbitalert.View.Scan.ScanActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {
            ScanActivityModule.class
    })
    abstract ScanActivity provideScanActivity();

}
