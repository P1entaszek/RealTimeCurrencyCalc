package com.prod.realtimecurrencycalc.utils.rx;


import rx.DefaultAppSchedulers;

public class AppSchedulersProvider {
    private static AppSchedulers Instance;

    public static AppSchedulers getInstance() {
        if (Instance == null) {
            Instance = new DefaultAppSchedulers();
        }
        return Instance;
    }
}
