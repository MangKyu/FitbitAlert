package com.fitbitalert.DI.Component;

import com.fitbitalert.Common.BaseApplication;
import com.fitbitalert.DI.Module.ActivityModule;
import com.fitbitalert.DI.Module.AppModule;
import com.fitbitalert.DI.Module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ActivityModule.class, AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(BaseApplication baseApplication);

        AppComponent build();
    }

}
