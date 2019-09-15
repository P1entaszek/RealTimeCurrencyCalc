package com.prod.realtimecurrencycalc.utils.rx;

import io.reactivex.Scheduler;

public interface AppSchedulers {
    Scheduler io();

    Scheduler ui();

    Scheduler newThread();

    Scheduler computation();
}
