package com.sultanseidov.locationforecast.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaximumModel {
    @SerializedName("Value")
    @Expose
    private Integer value;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("UnitType")
    @Expose
    private Integer unitType;

    public Integer getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public Integer getUnitType() {
        return unitType;
    }
}
