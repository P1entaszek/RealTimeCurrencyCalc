package com.prod.realtimecurrencycalc.utils.rx;

public class AppSchedulersProvider {
    private static AppSchedulers Instance;

    public static AppSchedulers getInstance() {
        if (Instance == null) {
            Instance = new DefaultAppSchedulers();
        }
        return Instance;
    }
}
