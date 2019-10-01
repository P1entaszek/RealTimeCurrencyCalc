package com.prod.realtimecurrencycalc.datasource.service;

import com.prod.realtimecurrencycalc.datasource.model.CurrenciesApiModel;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("latest")
    Observable<CurrenciesApiModel> getSearchedCurrencies(@Query("base") final @NonNull String currencyKey);
}
