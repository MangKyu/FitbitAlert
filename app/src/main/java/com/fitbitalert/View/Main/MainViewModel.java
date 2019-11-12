package com.fitbitalert.View.Main;

import com.fitbitalert.Common.BaseApplication;
import com.fitbitalert.View.Base.BaseViewModel;

import io.reactivex.disposables.CompositeDisposable;
import lombok.Builder;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    @Builder
    public MainViewModel(BaseApplication baseApplication, CompositeDisposable compositeDisposable) {
        super(baseApplication, compositeDisposable);
    }

}