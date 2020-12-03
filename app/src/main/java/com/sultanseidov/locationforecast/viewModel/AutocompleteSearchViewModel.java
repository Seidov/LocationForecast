package com.sultanseidov.locationforecast.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sultanseidov.locationforecast.repository.model.AutoCompleteSerachModel;
import com.sultanseidov.locationforecast.repository.service.ServiceClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AutocompleteSearchViewModel extends ViewModel {

    //AutocompleteSearch
    public MutableLiveData<List<AutoCompleteSerachModel>> resAutocompleteSearchModelMutableLiveData = new MutableLiveData<List<AutoCompleteSerachModel>>();
    public MutableLiveData<Boolean> resAutocompleteSearchModelMutableLiveDataLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> resAutocompleteSearchModelMutableLiveDataLoading = new MutableLiveData<Boolean>();

    CompositeDisposable disposable = new CompositeDisposable();
    ServiceClient service = ServiceClient.getInstance();

    public void refreshAutocompleteSearchModelData(String q) {
        fetchAutocompleteSearchModelData(q);
    }

    private void fetchAutocompleteSearchModelData(String q) {
        resAutocompleteSearchModelMutableLiveDataLoading.setValue(true);
        disposable.add(
                service.getAutocompleteSearch(ServiceClient.APIKEY, q, "en")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<AutoCompleteSerachModel>>() {

                            @Override
                            public void onSuccess(List<AutoCompleteSerachModel> objectList) {

                                if (objectList != null) {
                                    resAutocompleteSearchModelMutableLiveData.setValue(objectList);
                                    resAutocompleteSearchModelMutableLiveDataLoadError.setValue(false);
                                    resAutocompleteSearchModelMutableLiveDataLoading.setValue(false);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {

                                resAutocompleteSearchModelMutableLiveDataLoadError.setValue(true);
                                resAutocompleteSearchModelMutableLiveDataLoading.setValue(false);
                                //e.printStackTrace();
                                Log.e("AutocompleteSearchModel", e.getSuppressed().toString(), e);
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
