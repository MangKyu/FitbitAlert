package com.fitbitalert.DI.Module;

import com.fitbitalert.View.Main.MainActivity;
import com.fitbitalert.View.Main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class
    })
    abstract MainActivity provideMainActivity();

}
