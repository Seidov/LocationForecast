package com.sultanseidov.locationforecast.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyForecastModel {

    @SerializedName("Date")
    @Expose
    private String date;

    @SerializedName("EpochDate")
    @Expose
    private Integer epochDate;

    @SerializedName("Temperature")
    @Expose
    private TemperatureModel temperature;

    @SerializedName("Day")
    @Expose
    private DayModel day;

    @SerializedName("Night")
    @Expose
    private NightModel night;

    @SerializedName("Sources")
    @Expose
    private List<String> sources = null;

    @SerializedName("MobileLink")
    @Expose
    private String mobileLink;

    @SerializedName("Link")
    @Expose
    private String link;

    public String getDate() {
        return date;
    }

    public Integer getEpochDate() {
        return epochDate;
    }

    public TemperatureModel getTemperature() {
        return temperature;
    }

    public DayModel getDay() {
        return day;
    }

    public NightModel getNight() {
        return night;
    }

    public List<String> getSources() {
        return sources;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public String getLink() {
        return link;
    }
}
