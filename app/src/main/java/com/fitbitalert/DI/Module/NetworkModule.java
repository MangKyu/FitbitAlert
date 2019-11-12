package com.fitbitalert.DI.Module;

import com.fitbitalert.Network.Helper.NetworkHelper;
import com.fitbitalert.Network.Retrofit.RetrofitClient;
import com.fitbitalert.Network.Retrofit.RetrofitService;
import com.fitbitalert.Utils.Rx.AppRxScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    RetrofitService provideRetrofitService() {
        return new RetrofitClient().getService();
    }

    @Provides
    AppRxScheduler provideAppRxScheduler() {
        return new AppRxScheduler();
    }

    @Singleton
    @Provides
    NetworkHelper provideNetworkHelper(RetrofitService retrofitService, AppRxScheduler scheduler) {
        return new NetworkHelper(retrofitService, scheduler);
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

}
