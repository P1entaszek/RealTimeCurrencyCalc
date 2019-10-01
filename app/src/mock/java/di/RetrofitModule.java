package di;


import com.prod.realtimecurrencycalc.datasource.service.APIService;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitModule {

    @Provides
    public APIService provideAPIServiceMock() {
        return new APIServiceMock();
    }

}

