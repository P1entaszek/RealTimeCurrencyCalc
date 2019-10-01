package di;


import com.prod.realtimecurrencycalc.datasource.retrofit.APIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitModule {

    @Provides
    public APIService provideAPIServiceMock() {
        return new APIServiceMock();
    }

}

