package com.prod.realtimecurrencycalc.features.currencyList.mvp;

import com.prod.realtimecurrencycalc.datasource.model.CurrencyViewModel;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface CurrenciesListMVP {

    interface View{
        void showAllCurrencies(final @NonNull List<CurrencyViewModel> updatedCurrenciesMap);
        void showError(final @NonNull String errorMessage);
    }

    interface Presenter {
        void getCurrencies(String currencyKey, Double currencyMultiplier);

        void recalculateCurrencies(Double currencyMultiplier);
    }
}
