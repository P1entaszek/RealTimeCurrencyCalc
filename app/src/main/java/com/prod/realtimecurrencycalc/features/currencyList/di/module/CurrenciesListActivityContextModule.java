package com.prod.realtimecurrencycalc.features.currencyList.di.module;

import android.content.Context;

import com.prod.realtimecurrencycalc.features.currencyList.CurrenciesListActivity;
import com.prod.realtimecurrencycalc.qualifiers.ActivityContext;
import com.prod.realtimecurrencycalc.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CurrenciesListActivityContextModule {

    private CurrenciesListActivity currenciesListActivity;
    public Context context;

    public CurrenciesListActivityContextModule(CurrenciesListActivity currenciesListActivity) {
        this.currenciesListActivity = currenciesListActivity;
        context = currenciesListActivity;
    }

    @Provides
    @ActivityScope
    public CurrenciesListActivity providesCurrenciesListActivity() {
        return currenciesListActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }
}
