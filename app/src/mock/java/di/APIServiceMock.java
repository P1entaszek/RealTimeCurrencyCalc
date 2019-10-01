package di;

import com.prod.realtimecurrencycalc.TestUtils.TestUtils;
import com.prod.realtimecurrencycalc.datasource.model.CurrenciesApiModel;
import com.prod.realtimecurrencycalc.datasource.retrofit.APIService;

import io.reactivex.Observable;

/**
 * Created by Piotr Jaszczurowski on 30.09.2019.
 */
public class APIServiceMock implements APIService {


    @Override
    public Observable<CurrenciesApiModel> getSearchedCurrencies(String currencyKey) {
        CurrenciesApiModel getCurrencies = TestUtils.loadJson("mock/get_currencies.json", CurrenciesApiModel.class);
        return Observable.just(getCurrencies);
    }
}
