package com.prod.realtimecurrencycalc.features.currencyList;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.prod.realtimecurrencycalc.CurrencyApplication;
import com.prod.realtimecurrencycalc.R;
import com.prod.realtimecurrencycalc.datasource.model.CurrencyViewModel;
import com.prod.realtimecurrencycalc.di.component.ApplicationComponent;
import com.prod.realtimecurrencycalc.features.currencyList.di.component.CurrenciesListActivityComponent;
import com.prod.realtimecurrencycalc.features.currencyList.di.module.CurrenciesListActivityContextModule;
import com.prod.realtimecurrencycalc.features.currencyList.di.module.CurrenciesListActivityMVPModule;
import com.prod.realtimecurrencycalc.features.currencyList.mvp.CurrenciesListMVP;
import com.prod.realtimecurrencycalc.qualifiers.ActivityContext;
import com.prod.realtimecurrencycalc.qualifiers.ApplicationContext;

import java.util.List;

import javax.inject.Inject;

public class CurrenciesListActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener, CurrenciesListMVP.View, RecyclerViewAdapter.ClickEnterListener {

    private RecyclerView recyclerView;
    private final static Double initCurrencyMultiplier = 100.0;
    private final static String initCurrencyShortcut = "PLN";
    private CurrenciesListActivityComponent currenciesListActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    @ApplicationContext
    public Context appContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    CurrenciesListMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies_list);
        injectComponent();
        presenter.getCurrencies(initCurrencyShortcut, initCurrencyMultiplier);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);


    }


    private void injectComponent() {
        if (currenciesListActivityComponent == null) {
            Application application = getApplication();
            ApplicationComponent applicationComponent = ((CurrencyApplication) application)
                    .getApplicationComponent();
            currenciesListActivityComponent = applicationComponent
                    .getCurrencyComponentBuilder()
                    .getCurrenciesListActivityContextModule(new CurrenciesListActivityContextModule(this))
                    .getCurrenciesListActivityMVPModule(new CurrenciesListActivityMVPModule(this))
                    .build();
            currenciesListActivityComponent.injectCurrenciesListActivity(this);
        }
    }


    @Override
    public void updateData(String currencyName) {
        presenter.getCurrencies(currencyName, initCurrencyMultiplier);
    }


    @Override
    public void recalculateData(Double currencyMultiplier) {
        presenter.recalculateCurrencies(currencyMultiplier);
    }

    @Override
    public void showAllCurrencies(List<CurrencyViewModel> updatedCurrenciesMap, Double currencyMultiplier) {
        recyclerViewAdapter.setData(updatedCurrenciesMap, currencyMultiplier);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(activityContext, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
