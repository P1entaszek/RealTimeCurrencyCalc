package com.prod.realtimecurrencycalc.features.currencyList.mvp;

import android.util.Log;

import com.prod.realtimecurrencycalc.datasource.model.CurrenciesApiModel;
import com.prod.realtimecurrencycalc.datasource.model.CurrencyViewModel;
import com.prod.realtimecurrencycalc.datasource.retrofit.APIService;
import com.prod.realtimecurrencycalc.scopes.ActivityScope;
import com.prod.realtimecurrencycalc.utils.rx.AppSchedulersProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@ActivityScope
public class Presenter implements CurrenciesListMVP.Presenter {
    APIService apiService;
    CurrenciesListMVP.View view;

    @Inject
    public Presenter(APIService apiService, CurrenciesListMVP.View view) {
        this.apiService = apiService;
        this.view = view;
    }


    @Override
    public void getCurrencies(final String currencyKey) {
        apiService.
                getSearchedCurrencies(currencyKey)
                .subscribeOn(AppSchedulersProvider.getInstance().io())
                .observeOn(AppSchedulersProvider.getInstance().ui())
                .subscribe(new Observer<CurrenciesApiModel>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        //no-op
                    }

                    @Override
                    public void onNext(CurrenciesApiModel currenciesApiModel) {
                        view.showError("something");
                        ArrayList<String> keyList = new ArrayList<>(currenciesApiModel.getCurrenciesMap().keySet());
                        ArrayList<Double> valuesList = new ArrayList<>(currenciesApiModel.getCurrenciesMap().values());
                        List<CurrencyViewModel> currencies = new ArrayList<>();
                        for (int i=0; i<keyList.size(); i++) {
                            if(keyList.get(i).equals(currencyKey)){
                                currencies.add(0,new CurrencyViewModel("", keyList.get(i), "",  1.0));
                            }

                            else {
                                currencies.add(new CurrencyViewModel("", keyList.get(i), "", valuesList.get(i)));
                            }

                        }
                        view.showAllCurrencies(currencies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
