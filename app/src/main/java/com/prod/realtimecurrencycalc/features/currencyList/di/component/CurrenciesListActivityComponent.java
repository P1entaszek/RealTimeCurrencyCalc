package com.prod.realtimecurrencycalc.features.currencyList.di.component;

import android.content.Context;

import com.prod.realtimecurrencycalc.CurrencyApplication;
import com.prod.realtimecurrencycalc.di.component.ApplicationComponent;
import com.prod.realtimecurrencycalc.features.currencyList.CurrenciesListActivity;
import com.prod.realtimecurrencycalc.features.currencyList.di.module.AdapterModule;
import com.prod.realtimecurrencycalc.features.currencyList.di.module.CurrenciesListActivityMVPModule;
import com.prod.realtimecurrencycalc.qualifiers.ActivityContext;
import com.prod.realtimecurrencycalc.scopes.ActivityScope;
import com.prod.realtimecurrencycalc.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {AdapterModule.class, CurrenciesListActivityMVPModule.class}, dependencies = ApplicationComponent.class)
public interface CurrenciesListActivityComponent {


    @ActivityContext
    Context getContext();

    void injectCurrenciesListActivity(CurrenciesListActivity currenciesListActivity);}
