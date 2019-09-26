package com.prod.realtimecurrencycalc.features.currencyList.di.module;

import com.prod.realtimecurrencycalc.features.currencyList.CurrenciesListActivity;
import com.prod.realtimecurrencycalc.features.currencyList.RecyclerViewAdapter;
import com.prod.realtimecurrencycalc.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {CurrenciesListActivityContextModule.class})
public class AdapterModule {
    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCurrenciesList(RecyclerViewAdapter.LongClickListener longClickListener, RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(longClickListener, clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.LongClickListener getLongClickListener(CurrenciesListActivity currenciesListActivity) {
        return currenciesListActivity;
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(CurrenciesListActivity currenciesListActivity) {
        return currenciesListActivity;
    }
}
