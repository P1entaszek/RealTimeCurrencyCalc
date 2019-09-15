package com.prod.realtimecurrencycalc.di.module;

import android.content.Context;

import com.prod.realtimecurrencycalc.qualifiers.ApplicationContext;
import com.prod.realtimecurrencycalc.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
