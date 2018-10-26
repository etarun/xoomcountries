package com.redesign;

import android.app.Application;
import android.content.Context;

import com.redesign.dagger.AppComponent;
import com.redesign.dagger.AppModule;
import com.redesign.dagger.DaggerAppComponent;
import com.redesign.retrofit.RetrofitModule;

/**
 * Created by tarun on 10/24/18.
 */
import org.acra.*;
import org.acra.BuildConfig;
import org.acra.annotation.*;

/*
Main Application builds Dagger App component
 */
@AcraCore(buildConfigClass = BuildConfig.class)
public class MainXoomApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }
}
