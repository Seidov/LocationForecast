package com.sultanseidov.locationforecast.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NightModel {

    @SerializedName("Icon")
    @Expose
    private Integer icon;

    @SerializedName("IconPhrase")
    @Expose
    private String iconPhrase;

    @SerializedName("HasPrecipitation")
    @Expose
    private Boolean hasPrecipitation;

    @SerializedName("PrecipitationType")
    @Expose
    private String precipitationType;

    @SerializedName("PrecipitationIntensity")
    @Expose
    private String precipitationIntensity;

    public Integer getIcon() {
        return icon;
    }

    public String getIconPhrase() {
        return iconPhrase;
    }

    public Boolean getHasPrecipitation() {
        return hasPrecipitation;
    }

    public String getPrecipitationType() {
        return precipitationType;
    }

    public String getPrecipitationIntensity() {
        return precipitationIntensity;
    }
}
