package com.fitbitalert.View.Base;

import android.arch.lifecycle.AndroidViewModel;

import com.fitbitalert.Common.BaseApplication;
import com.fitbitalert.Network.Helper.NetworkHelper;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class BaseViewModel <N> extends AndroidViewModel {

    @Setter
    private NetworkHelper networkHelper;
    private WeakReference<N> navigator;
    private CompositeDisposable compositeDisposable;

    public BaseViewModel(BaseApplication baseApplication, CompositeDisposable compositeDisposable) {
        super(baseApplication);
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<>(navigator);
    }

}
