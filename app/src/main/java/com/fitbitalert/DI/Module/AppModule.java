package com.fitbitalert.DI.Module;

import android.content.Context;

import com.fitbitalert.Common.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(BaseApplication baseApplication){
        return baseApplication;
    }

}
