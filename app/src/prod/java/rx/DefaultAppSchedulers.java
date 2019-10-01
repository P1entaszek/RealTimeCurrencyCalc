package rx;

import com.prod.realtimecurrencycalc.utils.rx.AppSchedulers;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class DefaultAppSchedulers implements AppSchedulers {

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    @Override
    public Scheduler newThread() {
        return Schedulers.newThread();
    }

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }
}
