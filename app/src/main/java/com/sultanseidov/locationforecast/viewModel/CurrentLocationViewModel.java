package com.sultanseidov.locationforecast.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sultanseidov.locationforecast.repository.service.ServiceClient;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResDayOfDailyForecastsModel;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResGeopositionSearchModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CurrentLocationViewModel  extends ViewModel {

        CompositeDisposable disposable = new CompositeDisposable();

        ServiceClient service = ServiceClient.getInstance();

        //geopositionSearch
        public MutableLiveData<ResGeopositionSearchModel> geopositionSearchModelMutableLiveData = new MutableLiveData<ResGeopositionSearchModel>();
        public MutableLiveData<Boolean> geopositionSearchModelMutableLiveDataLoadError = new MutableLiveData<Boolean>();
        public MutableLiveData<Boolean> geopositionSearchModelMutableLiveDataDataLoading = new MutableLiveData<Boolean>();

        //dayOfDailyForecasts
        public MutableLiveData<ResDayOfDailyForecastsModel> dayOfDailyForecastsMutableLiveData = new MutableLiveData<ResDayOfDailyForecastsModel>();
        public MutableLiveData<Boolean> dayOfDailyForecastsMutableLiveDataLoadError = new MutableLiveData<Boolean>();
        public MutableLiveData<Boolean> dayOfDailyForecastsMutableLiveDataLoading = new MutableLiveData<Boolean>();

        public void refreshGeopositionSearchModelData(String latlon) {
            fetchGeopositionSearchModelData(latlon);}

        public void refreshDayOfDailyForecastsModelData(String urlCityKey) {
            fetchDayOfDailyForecastsModelData(urlCityKey);}

        private void fetchGeopositionSearchModelData(String latlon) {
            geopositionSearchModelMutableLiveDataDataLoading.setValue(true);
            disposable.add(
                    service.getGeopositionSearch(ServiceClient.APIKEY,latlon)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<ResGeopositionSearchModel>() {

                                @Override
                                public void onSuccess(ResGeopositionSearchModel objectList) {

                                    if (objectList!=null){
                                        geopositionSearchModelMutableLiveData.setValue(objectList);
                                        geopositionSearchModelMutableLiveDataLoadError.setValue(false);
                                        geopositionSearchModelMutableLiveDataDataLoading.setValue(false);
                                    }

                                }

                                @Override
                                public void onError(Throwable e) {

                                    geopositionSearchModelMutableLiveDataLoadError.setValue(true);
                                    geopositionSearchModelMutableLiveDataDataLoading.setValue(false);
                                    //e.printStackTrace();
                                    //Log.e("sultan", e.getSuppressed().toString());
                                    //Log.e("sultan", e.getMessage());
                                    //Log.e("sultan", e.getLocalizedMessage());
                                }
                            })
            );
        }

        private void fetchDayOfDailyForecastsModelData(String urlCityKey) {
            dayOfDailyForecastsMutableLiveDataLoading.setValue(true);
            String url;
            url="forecasts/v1/daily/1day/"+urlCityKey+"";
            disposable.add(
                    service.getDayOfDailyForecasts(url,ServiceClient.APIKEY,"en",false,false)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<ResDayOfDailyForecastsModel>() {

                                @Override
                                public void onSuccess(ResDayOfDailyForecastsModel objectList) {

                                    if (objectList!=null){
                                        dayOfDailyForecastsMutableLiveData.setValue(objectList);
                                        dayOfDailyForecastsMutableLiveDataLoadError.setValue(false);
                                        dayOfDailyForecastsMutableLiveDataLoading.setValue(false);
                                    }


                                }

                                @Override
                                public void onError(Throwable e) {

                                    dayOfDailyForecastsMutableLiveDataLoadError.setValue(true);
                                    dayOfDailyForecastsMutableLiveDataLoading.setValue(false);
                                    //e.printStackTrace();
                                    //Log.e("sultan", e.getSuppressed().toString());
                                    //Log.e("sultan", e.getMessage());
                                    //Log.e("sultan", e.getLocalizedMessage());
                                }
                            })
            );
        }

        @Override
        protected void onCleared() {
            super.onCleared();
            disposable.clear();
        }
    }
