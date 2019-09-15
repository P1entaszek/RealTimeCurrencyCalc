package com.prod.realtimecurrencycalc;

import android.app.Activity;
import android.app.Application;

import com.prod.realtimecurrencycalc.di.component.ApplicationComponent;
import com.prod.realtimecurrencycalc.di.component.DaggerApplicationComponent;
import com.prod.realtimecurrencycalc.di.module.ContextModule;

public class CurrencyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static CurrencyApplication get(Activity activity){
        return (CurrencyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
