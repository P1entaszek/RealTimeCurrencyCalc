package com.prod.realtimecurrencycalc.features.currencyList;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class CurrenciesListActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener, CurrenciesListMVP.View {

    private RecyclerView recyclerView;
    CurrenciesListActivityComponent currenciesListActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    CurrenciesListMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies_list);

        ApplicationComponent applicationComponent = CurrencyApplication.get(this).getApplicationComponent();
        currenciesListActivityComponent = DaggerCurrenciesListActivityComponent.builder()
                .mainActivityContextModule(new CurrenciesListActivityContextModule(this))
                .mainActivityMvpModule(new CurrenciesListActivityMVPModule(this))
                .applicationComponent(applicationComponent)
                .build();
        currenciesListActivityComponent.injectCurrenciesListActivity(this);
        
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        presenter.getCurrencies("EUR");
    }

    @Override
    public void updateData(String currencyName) {
        presenter.getCurrencies(currencyName);
    }

    @Override
    public void showAllCurrencies(List<CurrencyViewModel> updatedCurrenciesMap) {
            recyclerViewAdapter.setData(updatedCurrenciesMap);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(activityContext, "Unable to load data, check Your internet access", Toast.LENGTH_SHORT).show();
    }
}
