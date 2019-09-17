package com.prod.realtimecurrencycalc.features.currencyList.di.component;

import com.prod.realtimecurrencycalc.features.currencyList.CurrenciesListActivity;
import com.prod.realtimecurrencycalc.features.currencyList.di.module.AdapterModule;
import com.prod.realtimecurrencycalc.features.currencyList.di.module.CurrenciesListActivityContextModule;
import com.prod.realtimecurrencycalc.features.currencyList.di.module.CurrenciesListActivityMVPModule;
import com.prod.realtimecurrencycalc.features.currencyList.mvp.CurrenciesListMVP;
import com.prod.realtimecurrencycalc.scopes.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {AdapterModule.class, CurrenciesListActivityMVPModule.class})
public interface CurrenciesListActivityComponent {

    //CurrenciesListMVP.View provideView();

    void injectCurrenciesListActivity(CurrenciesListActivity currenciesListActivity);

    @Subcomponent.Builder
    interface Builder {
        CurrenciesListActivityComponent build();

        CurrenciesListActivityComponent.Builder getCurrenciesListActivityMVPModule(CurrenciesListActivityMVPModule currenciesListActivityMVPModule);

        CurrenciesListActivityComponent.Builder getCurrenciesListActivityContextModule(CurrenciesListActivityContextModule currenciesListActivityContextModule);

    }
}
