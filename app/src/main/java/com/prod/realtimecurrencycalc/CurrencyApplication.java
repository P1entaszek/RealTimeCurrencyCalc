package com.prod.realtimecurrencycalc;

import android.app.Application;

import com.prod.realtimecurrencycalc.di.component.ApplicationComponent;
import com.prod.realtimecurrencycalc.di.component.DaggerApplicationComponent;
import com.prod.realtimecurrencycalc.di.module.ContextModule;

public class CurrencyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null)
            applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        return applicationComponent;
    }
}
