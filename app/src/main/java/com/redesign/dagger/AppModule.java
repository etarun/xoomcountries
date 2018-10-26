package com.redesign.dagger;

import com.redesign.MainXoomApplication;
import com.redesign.retrofit.RetrofitModule;

import dagger.Module;


@Module(includes = RetrofitModule.class)
public class AppModule {
    private MainXoomApplication baseApplication;

    public AppModule(MainXoomApplication baseApplication) {
        this.baseApplication = baseApplication;
    }
}
