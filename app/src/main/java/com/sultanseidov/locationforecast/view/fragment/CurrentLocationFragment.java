package com.sultanseidov.locationforecast.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sultanseidov.locationforecast.R;
import com.sultanseidov.locationforecast.Util.Utils;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResDayOfDailyForecastsModel;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResGeopositionSearchModel;
import com.sultanseidov.locationforecast.view.activity.MainActivity;
import com.sultanseidov.locationforecast.viewModel.CurrentLocationViewModel;

public class CurrentLocationFragment extends Fragment {

    public static String TAG = CurrentLocationFragment.class.getSimpleName();
    public static LocationManager locationManager;
    public static Location myLocation;
    static View view;
    TextView textGotoSecondScreen;
    String stringDefaultCityKey="318251";
    CurrentLocationViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        viewModel = ViewModelProviders.of(getActivity()).get(CurrentLocationViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_current_location, container, false);
        initUIComponent();
        if (isGrantPermissions()) {
            viewModel.refreshGeopositionSearchModelData(getCurrentLocationAndRequestApi());
            observerGeopositionSearchViewModel();

        }

        return view;
    }

    private void observerGeopositionSearchViewModel() {
        viewModel.geopositionSearchModelMutableLiveData.observe(getActivity(), (ResGeopositionSearchModel resGeopositionSearchModel) -> {

            if (resGeopositionSearchModel != null) {
                viewModel.refreshDayOfDailyForecastsModelData(resGeopositionSearchModel.getKey());
                observerDayOfDailyForecastsViewModel();

                textGotoSecondScreen.setText(resGeopositionSearchModel.getKey().toString());

            } else {
                Log.i(TAG, "resGeopositionSearchModel.getKey() null");
            }


        });

        viewModel.geopositionSearchModelMutableLiveDataLoadError.observe(getActivity(), isError -> {
            if (isError != null) {

            }
        });

        viewModel.geopositionSearchModelMutableLiveDataDataLoading.observe(getActivity(), isLoading -> {
            if (isLoading != null) {

            }
        });
    }

    private void observerDayOfDailyForecastsViewModel() {
        viewModel.dayOfDailyForecastsMutableLiveData.observe(this, (ResDayOfDailyForecastsModel resDayOfDailyForecastsModel) -> {

            if (resDayOfDailyForecastsModel != null) {


                Toast.makeText(getContext(), "notnull", Toast.LENGTH_SHORT).show();

            } else {


                Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
            }


        });

        viewModel.dayOfDailyForecastsMutableLiveDataLoadError.observe(this, isError -> {
            if (isError != null) {

            }
        });

        viewModel.dayOfDailyForecastsMutableLiveDataLoading.observe(this, isLoading -> {
            if (isLoading != null) {

            }
        });
    }

    public static String getCurrentLocationAndRequestApi() {
        String stringResultLatLon = null;
        Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSION successful.......");

        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (myLocation != null) {
                stringResultLatLon= myLocation.getLatitude()+","+myLocation.getLongitude()+"";
                Toast.makeText(view.getContext(), ""+stringResultLatLon, Toast.LENGTH_SHORT).show();
            }
        }
        return stringResultLatLon;
    }

    private boolean isGrantPermissions() {
        boolean isPermitGrant;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            isPermitGrant = Utils.isGrantLocationPermissions(requireActivity(), Utils.permissionRequest1, Utils.permissionRequestCode1);
        } else {
            isPermitGrant = Utils.isGrantLocationPermissions(requireActivity(), Utils.permissionRequest2, Utils.permissionRequestCode2);
        }
        return isPermitGrant;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textGotoSecondScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isGrantPermissions();

                //Navigation.findNavController(view).navigate(R.id.action_blankFragment_to_blankFragment2);
            }
        });

    }

    private void initUIComponent() {
        textGotoSecondScreen=view.findViewById(R.id.textGotoSecondScreen);
    }
}