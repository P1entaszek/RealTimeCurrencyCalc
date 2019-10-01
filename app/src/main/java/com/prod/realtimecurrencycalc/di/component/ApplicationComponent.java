package com.prod.realtimecurrencycalc.di.component;

import com.prod.realtimecurrencycalc.CurrencyApplication;
import com.prod.realtimecurrencycalc.datasource.service.APIService;
import di.RetrofitModule;
import com.prod.realtimecurrencycalc.di.module.ContextModule;
import com.prod.realtimecurrencycalc.features.currencyList.di.component.CurrenciesListActivityComponent;
import com.prod.realtimecurrencycalc.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIService getApiService();

    void injectApplication(CurrencyApplication myApplication);

    CurrenciesListActivityComponent.Builder getCurrencyComponentBuilder();
}
