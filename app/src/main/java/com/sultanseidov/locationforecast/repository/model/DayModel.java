package com.sultanseidov.locationforecast.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayModel {
    @SerializedName("Icon")
    @Expose
    private Integer icon;

    @SerializedName("IconPhrase")
    @Expose
    private String iconPhrase;

    @SerializedName("HasPrecipitation")
    @Expose
    private Boolean hasPrecipitation;

    public Integer getIcon() {
        return icon;
    }

    public String getIconPhrase() {
        return iconPhrase;
    }

    public Boolean getHasPrecipitation() {
        return hasPrecipitation;
    }
}
