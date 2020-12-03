package com.sultanseidov.locationforecast.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sultanseidov.locationforecast.R;
import com.sultanseidov.locationforecast.Util.Utils;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResDayOfDailyForecastsModel;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResGeopositionSearchModel;
import com.sultanseidov.locationforecast.view.adapter.FiveDaysofDailyForecastsAdapter;
import com.sultanseidov.locationforecast.viewModel.LocationForecastViewModel;

import java.util.ArrayList;

public class CurrentLocationFragment extends Fragment {

    public static String TAG = CurrentLocationFragment.class.getSimpleName();
    public static LocationManager locationManager;
    public static Location myLocation;
    static View view;
    ResGeopositionSearchModel geopositionSearchModel;
    TextView textCurrentTemperature;
    TextView textCurrentDay;
    TextView textCurrentLocation;
    ImageView imageCurrentDay;
    RecyclerView rvNextDays;
    LinearLayout linearSearchLayout;
    TextView textView;
    TextView textErrorMessage;
    ProgressBar pbCurrentLocation;

    String stringDefaultCityKey = "318251";
    LocationForecastViewModel viewModel;
    FiveDaysofDailyForecastsAdapter adapter = new FiveDaysofDailyForecastsAdapter(new ArrayList<>());

    public static String getCurrentLocationAndRequestApi() {
        String stringResultLatLon = null;
        Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSION successful.......");

        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (myLocation != null) {
                stringResultLatLon = myLocation.getLatitude() + "," + myLocation.getLongitude() + "";
                //Toast.makeText(view.getContext(), "" + stringResultLatLon, Toast.LENGTH_SHORT).show();
            }
        }
        return stringResultLatLon;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_current_location, container, false);
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        viewModel = ViewModelProviders.of(getActivity()).get(LocationForecastViewModel.class);
        initUIComponent();
        initNextDaysRecyclerView();
        if (isGrantPermissions()) {
            viewModel.refreshGeopositionSearchModelData(getCurrentLocationAndRequestApi());
            observerGeopositionSearchViewModel();

        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        linearSearchLayout.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_blankFragment_to_blankFragment2);
        });

    }

    private void observerGeopositionSearchViewModel() {
        viewModel.geopositionSearchModelMutableLiveData.observe(getActivity(), (ResGeopositionSearchModel resGeopositionSearchModel) -> {

            if (resGeopositionSearchModel != null) {
                geopositionSearchModel = resGeopositionSearchModel;
                viewModel.refreshDayOfDailyForecastsModelData(resGeopositionSearchModel.getKey());
                observerDayOfDailyForecastsViewModel();


            } else {


            }


        });

        viewModel.geopositionSearchModelMutableLiveDataLoadError.observe(getActivity(), isError -> {
            if (isError != null) {
                textErrorMessage.setVisibility(isError.isError() ? View.VISIBLE : View.GONE);

                if (isError.isError()) {

                    textCurrentDay.setVisibility(View.GONE);
                    textCurrentLocation.setVisibility(View.GONE);
                    textCurrentTemperature.setVisibility(View.GONE);
                    imageCurrentDay.setVisibility(View.GONE);
                    rvNextDays.setVisibility(View.GONE);
                    linearSearchLayout.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);

                    textErrorMessage.setText(isError.getThrowable().getMessage());

                } else {

                    textCurrentDay.setVisibility(View.VISIBLE);
                    textCurrentLocation.setVisibility(View.VISIBLE);
                    textCurrentTemperature.setVisibility(View.VISIBLE);
                    imageCurrentDay.setVisibility(View.VISIBLE);
                    rvNextDays.setVisibility(View.VISIBLE);
                    linearSearchLayout.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }


            }

        });

        viewModel.geopositionSearchModelMutableLiveDataDataLoading.observe(getActivity(), isLoading -> {
            if (isLoading != null) {
                if (isLoading) {

                    pbCurrentLocation.setVisibility(View.VISIBLE);


                } else {
                    pbCurrentLocation.setVisibility(View.GONE);


                }

            }
        });
    }

    private void observerDayOfDailyForecastsViewModel() {
        viewModel.dayOfDailyForecastsMutableLiveData.observe(this, (ResDayOfDailyForecastsModel resDayOfDailyForecastsModel) -> {

            if (resDayOfDailyForecastsModel != null) {

                textCurrentDay.setText(Utils.getDateNameByEpochDate(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getEpochDate()));
                textCurrentLocation.setText(geopositionSearchModel.getLocalizedName() + "\n" + geopositionSearchModel.getAdministrativeArea().getLocalizedName() + ", " + geopositionSearchModel.getCountry().getCountryLocalizedName());
                textCurrentTemperature.setText(Utils.getCelsiusByFahrenheit(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getTemperature().getMinimum().getValue(), resDayOfDailyForecastsModel.getDailyForecasts().get(0).getTemperature().getMaximum().getValue()) + "°");

                imageCurrentDay.setImageResource(Utils.getWeatherImageByIconId(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getDay().getIcon()));
                observerNextDays(resDayOfDailyForecastsModel);


            } else {


            }


        });

        viewModel.dayOfDailyForecastsMutableLiveDataLoadError.observe(this, isError -> {
            if (isError != null) {
                textErrorMessage.setVisibility(isError.isError() ? View.VISIBLE : View.GONE);


                if (isError.isError()) {

                    textCurrentDay.setVisibility(View.GONE);
                    textCurrentLocation.setVisibility(View.GONE);
                    textCurrentTemperature.setVisibility(View.GONE);
                    imageCurrentDay.setVisibility(View.GONE);
                    rvNextDays.setVisibility(View.GONE);
                    linearSearchLayout.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);

                    textErrorMessage.setText(isError.getThrowable().getMessage());


                } else {

                    textCurrentDay.setVisibility(View.VISIBLE);
                    textCurrentLocation.setVisibility(View.VISIBLE);
                    textCurrentTemperature.setVisibility(View.VISIBLE);
                    imageCurrentDay.setVisibility(View.VISIBLE);
                    rvNextDays.setVisibility(View.VISIBLE);
                    linearSearchLayout.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }


            }
        });

        viewModel.dayOfDailyForecastsMutableLiveDataLoading.observe(this, isLoading -> {
            if (isLoading != null) {
                if (isLoading) {

                    pbCurrentLocation.setVisibility(View.VISIBLE);
                    textErrorMessage.setVisibility(View.GONE);


                } else {
                    pbCurrentLocation.setVisibility(View.GONE);
                    textErrorMessage.setVisibility(View.GONE);


                }

            }
        });
    }

    private void observerNextDays(ResDayOfDailyForecastsModel resDayOfDailyForecastsModel) {
        adapter.updateFiveDaysofDailyForecastsAdapter(resDayOfDailyForecastsModel.getDailyForecasts());
    }

    private void initNextDaysRecyclerView() {
        rvNextDays.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvNextDays.setAdapter(adapter);
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

    private void initUIComponent() {

        textCurrentTemperature = view.findViewById(R.id.textCurrentTemperature);
        textCurrentDay = view.findViewById(R.id.textCurrentDay);
        textCurrentLocation = view.findViewById(R.id.textCurrentLocation);

        imageCurrentDay = view.findViewById(R.id.imageCurrentDay);
        rvNextDays = view.findViewById(R.id.rvNextDays);

        linearSearchLayout = view.findViewById(R.id.linearSearchLayout);

        textErrorMessage = view.findViewById(R.id.textErrorMessage);
        pbCurrentLocation = view.findViewById(R.id.pbCurrentLocation);

        textView = view.findViewById(R.id.textView);


    }
}