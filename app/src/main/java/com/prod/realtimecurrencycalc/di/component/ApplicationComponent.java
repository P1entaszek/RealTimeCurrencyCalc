package com.prod.realtimecurrencycalc.di.component;

import android.content.Context;

import com.prod.realtimecurrencycalc.CurrencyApplication;
import com.prod.realtimecurrencycalc.datasource.retrofit.APIService;
import com.prod.realtimecurrencycalc.datasource.retrofit.di.RetrofitModule;
import com.prod.realtimecurrencycalc.di.module.ContextModule;
import com.prod.realtimecurrencycalc.qualifiers.ApplicationContext;
import com.prod.realtimecurrencycalc.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIService getApiService();

    @ApplicationContext
    Context getContext();

    void injectApplication(CurrencyApplication myApplication);
}
