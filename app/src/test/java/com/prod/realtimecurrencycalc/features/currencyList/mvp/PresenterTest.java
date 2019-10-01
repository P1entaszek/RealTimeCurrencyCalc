package com.prod.realtimecurrencycalc.features.currencyList.mvp;

import com.prod.realtimecurrencycalc.features.currencyList.CurrenciesListActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import di.APIServiceMock;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Piotr Jaszczurowski on 01.10.2019.
 */
public class PresenterTest {

    private Presenter presenter;

    @Mock
    private CurrenciesListActivity view;

    @Mock
    private APIServiceMock apiServiceMock;

    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        MockitoAnnotations.initMocks(this);
        apiServiceMock = Mockito.spy(new APIServiceMock());
        presenter = Mockito.spy(new Presenter(apiServiceMock, view));
    }

    @Test
    public void getCurrenciesFromApiServiceMock() {
        Mockito.doReturn(new ArrayList<>()).when(presenter).getAllCountriesForCurrencies();
        presenter.getCurrencies("PLN", 1.0);
        Mockito.verify(apiServiceMock).getSearchedCurrencies(Mockito.anyString());
        Mockito.verify(view, Mockito.times(1)).showAllCurrencies(Mockito.anyList(), Mockito.anyDouble());
    }
}