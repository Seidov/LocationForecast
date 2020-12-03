package com.sultanseidov.locationforecast.repository.service.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sultanseidov.locationforecast.repository.model.DailyForecastModel;
import com.sultanseidov.locationforecast.repository.model.HeadlineModel;

import java.util.List;

public class ResDayOfDailyForecastsModel {
    @SerializedName("Headline")
    @Expose
    private HeadlineModel headline;
    @SerializedName("DailyForecasts")
    @Expose
    private List<DailyForecastModel> dailyForecasts = null;

    public HeadlineModel getHeadline() {
        return headline;
    }

    public List<DailyForecastModel> getDailyForecasts() {
        return dailyForecasts;
    }
}
