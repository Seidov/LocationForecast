package com.sultanseidov.locationforecast.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.sultanseidov.locationforecast.R;
import com.sultanseidov.locationforecast.Util.Utils;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResDayOfDailyForecastsModel;
import com.sultanseidov.locationforecast.repository.service.responseModel.ResSearchByLocationKeyModel;
import com.sultanseidov.locationforecast.viewModel.LocationForecastViewModel;

public class DetailForecastFragment extends Fragment {

    View view;
    ConstraintLayout constraintLayout;

    TextView textLocalizedName;
    TextView textAALocalizedName;
    TextView textCountryLocalizedName;
    TextView textDetailForecatsCurrentDate;

    ImageView imageViewDetailForecatsIcon;
    TextView textDetailForecatsTemperature;
    TextView textDetailForecatsPhrase;

    LocationForecastViewModel viewModel;
    String cityKey = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityKey = getArguments().getString("cityKey");
        viewModel = ViewModelProviders.of(getActivity()).get(LocationForecastViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_forecast, container, false);
        initUIComponent();

        viewModel.refreshDayOfDailyForecastsModelData(cityKey);
        viewModel.refreshSearchByLocationKeyModelData(cityKey);

        observerDayOfDailyForecastsViewModel();
        observerSearchByLocationKeyViewModel();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void observerDayOfDailyForecastsViewModel() {
        viewModel.dayOfDailyForecastsMutableLiveData.observe(this, (ResDayOfDailyForecastsModel resDayOfDailyForecastsModel) -> {

            if (resDayOfDailyForecastsModel != null) {

                textDetailForecatsCurrentDate.setText(Utils.getDateNameByEpochDate(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getEpochDate()));
                textDetailForecatsTemperature.setText(Utils.getCelsiusByFahrenheit(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getTemperature().getMinimum().getValue(),resDayOfDailyForecastsModel.getDailyForecasts().get(0).getTemperature().getMaximum().getValue())+"°");
                imageViewDetailForecatsIcon.setImageResource(Utils.getIconByIconId(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getDay().getIcon()));
                textDetailForecatsPhrase.setText(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getDay().getIconPhrase());

                if (Utils.isDay(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getEpochDate())) {
                    constraintLayout.setBackgroundResource(Utils.getImageByDayOrNight(true));
                } else {
                    constraintLayout.setBackgroundResource(Utils.getImageByDayOrNight(false));
                }


                //Toast.makeText(getContext(), "notnull", Toast.LENGTH_SHORT).show();

            } else {

                //Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
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

    private void observerSearchByLocationKeyViewModel() {

        viewModel.searchByLocationKeyModelMutableLiveData.observe(this, (ResSearchByLocationKeyModel resSearchByLocationKeyModel) -> {

            if (resSearchByLocationKeyModel != null) {

                textLocalizedName.setText(resSearchByLocationKeyModel.getLocalizedName());
                textAALocalizedName.setText(resSearchByLocationKeyModel.getAdministrativeArea().getLocalizedName());
                textCountryLocalizedName.setText(resSearchByLocationKeyModel.getCountry().getCountryLocalizedName());

                Toast.makeText(getContext(), " searchByLocationKeyModelMutableLiveData notnull", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(getContext(), "searchByLocationKeyModelMutableLiveData null", Toast.LENGTH_SHORT).show();
            }


        });

        viewModel.searchByLocationKeyModelMutableLiveDataLoadError.observe(this, isError -> {
            if (isError != null) {

            }
        });

        viewModel.searchByLocationKeyModelMutableLiveDataLoading.observe(this, isLoading -> {
            if (isLoading != null) {

            }
        });
    }

    private void initUIComponent() {

        constraintLayout = view.findViewById(R.id.constraintLayout);
        textLocalizedName = view.findViewById(R.id.textLocalizedName);
        textAALocalizedName = view.findViewById(R.id.textAALocalizedName);
        textCountryLocalizedName = view.findViewById(R.id.textCountryLocalizedName);
        textDetailForecatsCurrentDate = view.findViewById(R.id.textDetailForecatsCurrentDate);

        imageViewDetailForecatsIcon = view.findViewById(R.id.imageViewDetailForecatsIcon);
        textDetailForecatsTemperature = view.findViewById(R.id.textDetailForecatsTemperature);
        textDetailForecatsPhrase = view.findViewById(R.id.textDetailForecatsPhrase);
    }

}