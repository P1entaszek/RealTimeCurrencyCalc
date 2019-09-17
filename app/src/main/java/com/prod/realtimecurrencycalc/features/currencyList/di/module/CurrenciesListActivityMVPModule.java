package com.prod.realtimecurrencycalc.features.currencyList.di.module;

import com.prod.realtimecurrencycalc.datasource.retrofit.APIService;
import com.prod.realtimecurrencycalc.features.currencyList.mvp.CurrenciesListMVP;
import com.prod.realtimecurrencycalc.features.currencyList.mvp.Presenter;
import com.prod.realtimecurrencycalc.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CurrenciesListActivityMVPModule {
    private final CurrenciesListMVP.View view;

    public CurrenciesListActivityMVPModule(CurrenciesListMVP.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    CurrenciesListMVP.View provideView() {
        return view;
    }

    @Provides
    @ActivityScope
    CurrenciesListMVP.Presenter providePresenter(APIService service, CurrenciesListMVP.View view) {
        return new Presenter(service, view);
    }
}
