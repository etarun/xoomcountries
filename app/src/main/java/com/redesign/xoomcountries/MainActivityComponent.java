package com.redesign.xoomcountries;

import com.redesign.dagger.AppComponent;
import com.redesign.scope.ActivityScope;

import dagger.Component;


@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {MainActivityModule.class}
)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}