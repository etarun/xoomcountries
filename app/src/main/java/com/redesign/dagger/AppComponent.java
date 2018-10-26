package com.redesign.dagger;

import com.redesign.BaseActivity;
import com.redesign.data.Repository;
import com.redesign.retrofit.RetrofitModule;
import com.redesign.scope.ApplicationScope;
import com.redesign.xoomcountries.MainActivityModule;

import dagger.Component;

/**
 * Dagger App Component with Application Scope
 */

@ApplicationScope
@Component(modules = {AppModule.class, RetrofitModule.class, MainActivityModule.class})
public interface AppComponent {
    Repository getDataRepository();

    void inject(BaseActivity baseActivity);
}

