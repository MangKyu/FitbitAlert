package com.fitbitalert.View.Main;

import com.fitbitalert.Common.BaseApplication;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(BaseApplication baseApplication, CompositeDisposable compositeDisposable){
        return MainViewModel.builder()
                .baseApplication(baseApplication)
                .compositeDisposable(compositeDisposable)
                .build();
    }

}
