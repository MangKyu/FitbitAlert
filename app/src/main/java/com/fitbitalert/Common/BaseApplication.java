package com.fitbitalert.Common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.fitbitalert.DI.Component.DaggerAppComponent;
import com.polidea.rxandroidble2.LogConstants;
import com.polidea.rxandroidble2.LogOptions;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.exceptions.BleException;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

public class BaseApplication extends Application implements HasActivityInjector {

    public static boolean DEBUG;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    private RxBleClient rxBleClient;

    public static RxBleClient getRxBleClient(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.rxBleClient;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        DEBUG = isDebuggable(this);
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        rxBleClient = RxBleClient.create(this);
        RxBleClient.updateLogOptions(new LogOptions.Builder()
                .setLogLevel(LogConstants.INFO)
                .setMacAddressLogSetting(LogConstants.MAC_ADDRESS_FULL)
                .setUuidsLogSetting(LogConstants.UUIDS_FULL)
                .setShouldLogAttributeValues(true)
                .build()
        );
        RxJavaPlugins.setErrorHandler(throwable -> {
            if (throwable instanceof UndeliverableException && throwable.getCause() instanceof BleException) {
                Log.v("SampleApplication", "Suppressed UndeliverableException: " + throwable.toString());
                return; // ignore BleExceptions as they were surely delivered at least once
            }
            // add other custom handlers if needed
            throw new RuntimeException("Unexpected Throwable in RxJavaPlugins error handler", throwable);
        });
    }

    /**
     * Returns whether currently debug mode is on or not.
     *
     * @param context
     * @return
     */
    private boolean isDebuggable(Context context) {
        boolean debuggable = true;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /* debuggable variable will remain false */
        }

        return debuggable;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

}
