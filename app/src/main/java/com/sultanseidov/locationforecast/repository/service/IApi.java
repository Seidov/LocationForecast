package com.sultanseidov.locationforecast.repository.service;

import com.sultanseidov.locationforecast.repository.model.AutoCompleteSerachModel;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResDayOfDailyForecastsModel;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResGeopositionSearchModel;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResSearchByLocationKeyModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IApi {

    //getGeopositionSearch
    @GET("locations/v1/cities/geoposition/search?language=en&toplevel=false&details=false")
    Single<ResGeopositionSearchModel> getGeopositionSearch(@Query("apikey") String apikey,
                                                           @Query("q") String q);

    //AutocompleteSearch
    @GET("locations/v1/cities/autocomplete")
    Single<List<AutoCompleteSerachModel>> getAutocompleteSearch(@Query("apikey") String apikey,
                                                                @Query("q") String q,
                                                                @Query("language")String language);

    //Daily Forecasts
    @GET()
    Single<ResDayOfDailyForecastsModel> getDayOfDailyForecasts(@Url String cityKey,
                                                               @Query("apikey") String apikey,
                                                               @Query("language")String language,
                                                               @Query("details")boolean details,
                                                               @Query("metric")boolean metric);


    //Search by locationKey
    @GET()
    Single<ResSearchByLocationKeyModel> getSearchByLocationKey(@Url String cityKey,
                                                               @Query("apikey") String apikey,
                                                               @Query("language")String language);




}
