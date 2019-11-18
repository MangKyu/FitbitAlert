package com.fitbitalert.DI.Module;

import android.content.Context;

import com.fitbitalert.Common.BaseApplication;
import com.fitbitalert.Utils.Logs.Dlog;
import com.polidea.rxandroidble2.LogConstants;
import com.polidea.rxandroidble2.LogOptions;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.exceptions.BleException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(BaseApplication baseApplication){
        return baseApplication;
    }

    @Provides
    @Singleton
    RxBleClient provideRxBleClient(BaseApplication baseApplication){
        RxBleClient rxBleClient = RxBleClient.create(baseApplication);
        RxBleClient.updateLogOptions(new LogOptions.Builder()
                .setLogLevel(LogConstants.INFO)
                .setMacAddressLogSetting(LogConstants.MAC_ADDRESS_FULL)
                .setUuidsLogSetting(LogConstants.UUIDS_FULL)
                .setShouldLogAttributeValues(true)
                .build()
        );

        RxJavaPlugins.setErrorHandler(throwable -> {
            if (throwable instanceof UndeliverableException && throwable.getCause() instanceof BleException) {
                Dlog.e("Suppressed UndeliverableException: " + throwable.toString());
                return; // ignore BleExceptions as they were surely delivered at least once
            }
            // add other custom handlers if needed
            throw new RuntimeException("Unexpected Throwable in RxJavaPlugins error handler", throwable);
        });

        return rxBleClient;
    }
}
