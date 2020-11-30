package com.sultanseidov.locationforecast.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TemperatureModel {
    @SerializedName("Minimum")
    @Expose
    private MinimumModel minimum;
    @SerializedName("Maximum")
    @Expose
    private MaximumModel maximum;

    public MinimumModel getMinimum() {
        return minimum;
    }

    public MaximumModel getMaximum() {
        return maximum;
    }
}
