package com.sultanseidov.locationforecast.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.sultanseidov.locationforecast.R;
import com.sultanseidov.locationforecast.Util.Utils;
import com.sultanseidov.locationforecast.repository.model.AutoCompleteSerachModel;
import com.sultanseidov.locationforecast.repository.room.DatabaseClient;
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

    TextView textErrorMessage;
    ProgressBar progressBarDetaisForecast;

    LinearLayout linearBack;

    AutoCompleteSerachModel autoCompleteSerachModel;
    LocationForecastViewModel viewModel;
    String cityKey = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityKey = getArguments().getString("cityKey");
        autoCompleteSerachModel = getArguments().getParcelable("autoCompleteSerachModel");
        setLastQuery(autoCompleteSerachModel);

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
        linearBack.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_detailForecastFragment_pop);
        });

    }

    private void observerDayOfDailyForecastsViewModel() {
        viewModel.dayOfDailyForecastsMutableLiveData.observe(this, (ResDayOfDailyForecastsModel resDayOfDailyForecastsModel) -> {

            if (resDayOfDailyForecastsModel != null) {

                textDetailForecatsCurrentDate.setText(Utils.getDateNameByEpochDate(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getEpochDate()));
                textDetailForecatsTemperature.setText(Utils.getCelsiusByFahrenheit(resDayOfDailyForecastsModel.getDailyForecasts().get(0).getTemperature().getMinimum().getValue(), resDayOfDailyForecastsModel.getDailyForecasts().get(0).getTemperature().getMaximum().getValue()) + "°");
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
                textErrorMessage.setVisibility(isError.isError() ? View.VISIBLE : View.GONE);

                if (isError.isError()) {

                    textDetailForecatsCurrentDate.setVisibility(View.GONE);
                    textDetailForecatsTemperature.setVisibility(View.GONE);
                    imageViewDetailForecatsIcon.setVisibility(View.GONE);
                    textDetailForecatsPhrase.setVisibility(View.GONE);
                    constraintLayout.setVisibility(View.GONE);

                    textErrorMessage.setText(isError.getThrowable().getMessage());


                }
            }
        });

        viewModel.dayOfDailyForecastsMutableLiveDataLoading.observe(this, isLoading -> {
            if (isLoading != null) {
                if (isLoading) {

                    progressBarDetaisForecast.setVisibility(View.VISIBLE);

                    textDetailForecatsCurrentDate.setVisibility(View.GONE);
                    textDetailForecatsTemperature.setVisibility(View.GONE);
                    imageViewDetailForecatsIcon.setVisibility(View.GONE);
                    textDetailForecatsPhrase.setVisibility(View.GONE);
                    constraintLayout.setVisibility(View.GONE);


                } else {
                    progressBarDetaisForecast.setVisibility(View.GONE);


                    textDetailForecatsCurrentDate.setVisibility(View.VISIBLE);
                    textDetailForecatsTemperature.setVisibility(View.VISIBLE);
                    imageViewDetailForecatsIcon.setVisibility(View.VISIBLE);
                    textDetailForecatsPhrase.setVisibility(View.VISIBLE);
                    constraintLayout.setVisibility(View.VISIBLE);


                }
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
                textErrorMessage.setVisibility(isError.isError() ? View.VISIBLE : View.GONE);

                if (isError.isError()) {

                    textDetailForecatsCurrentDate.setVisibility(View.GONE);
                    textDetailForecatsTemperature.setVisibility(View.GONE);
                    imageViewDetailForecatsIcon.setVisibility(View.GONE);
                    textDetailForecatsPhrase.setVisibility(View.GONE);
                    constraintLayout.setVisibility(View.GONE);

                    textErrorMessage.setText(isError.getThrowable().getMessage());


                }
            }
        });

        viewModel.searchByLocationKeyModelMutableLiveDataLoading.observe(this, isLoading -> {
            if (isLoading != null) {
                if (isLoading) {

                    progressBarDetaisForecast.setVisibility(View.VISIBLE);
                    textErrorMessage.setVisibility(View.GONE);


                } else {
                    progressBarDetaisForecast.setVisibility(View.GONE);
                    textErrorMessage.setVisibility(View.GONE);


                }
            }
        });
    }

    private void setLastQuery(AutoCompleteSerachModel resAutocompleteSearchModel) {
        class SaveTask extends AsyncTask<Void, Void, Void> {

            String msg = "";

            @Override
            protected Void doInBackground(Void... voids) {
                AutoCompleteSerachModel existModel = DatabaseClient.getInstance(getContext())
                        .getAppDatabase().autoCompleteSearchDao()
                        .getAllautoCompleteSerachModelById(autoCompleteSerachModel.getKey());
                if (existModel == null) {
                    //adding to database
                    DatabaseClient.getInstance(getContext()).getAppDatabase()
                            .autoCompleteSearchDao()
                            .insert(resAutocompleteSearchModel);
                    msg = autoCompleteSerachModel.getLocalizedName() + "is SAVED to Last Search";
                } else {
                    msg = autoCompleteSerachModel.getLocalizedName() + "is exist in DB";
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));


            }
        }

        SaveTask st = new SaveTask();
        st.execute();
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

        textErrorMessage = view.findViewById(R.id.textErrorMessage);
        progressBarDetaisForecast = view.findViewById(R.id.progressBarDetaisForecast);

        linearBack = view.findViewById(R.id.linearBack);

    }

}