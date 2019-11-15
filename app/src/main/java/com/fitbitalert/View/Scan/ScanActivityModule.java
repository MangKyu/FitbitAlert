package com.fitbitalert.View.Scan;

import android.databinding.ObservableBoolean;

import com.fitbitalert.Common.BaseApplication;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ScanActivityModule {

    @Provides
    ScanViewModel provideScanViewModel(BaseApplication baseApplication, CompositeDisposable compositeDisposable){
        return ScanViewModel.builder()
                .baseApplication(baseApplication)
                .compositeDisposable(compositeDisposable)
                .observableBoolean(new ObservableBoolean(false))
                .build();
    }

    @Provides
    ScanAdapter provideScanAdapter(){
        return new ScanAdapter();
    }

}
