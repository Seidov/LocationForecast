package com.sultanseidov.locationforecast.repository.service;

import com.sultanseidov.locationforecast.repository.model.AutoCompleteSerachModel;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResDayOfDailyForecastsModel;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResGeopositionSearchModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    public static String BASE_URL = "http://dataservice.accuweather.com/";
    //public static String APIKEY="V7vxIQiXA6gpoB7PIJrcpmpSpTwrcOIR";
    public static String APIKEY="my38hJVxUYKPMQUM4J8LSdPAxOZaWxuF";


    private static ServiceClient instance;

    public IApi api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(IApi.class);

    private ServiceClient() {
    }

    public static ServiceClient getInstance() {
        if (instance == null) {
            instance = new ServiceClient();
        }
        return instance;
    }


    public Single<ResGeopositionSearchModel> getGeopositionSearch(String apikey, String q) {
        return api.getGeopositionSearch(apikey,q);
    }

    public Single<ResDayOfDailyForecastsModel> getDayOfDailyForecasts(String urlCityKey, String apikey, String language, boolean details, boolean metric) {
        return api.getDayOfDailyForecasts(urlCityKey,apikey, language, details, metric);
    }

    public Single<List<AutoCompleteSerachModel>> getAutocompleteSearch(String apikey, String q, String language) {
        return api.getAutocompleteSearch(apikey,q, language);
    }
}
