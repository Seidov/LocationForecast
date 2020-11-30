package com.sultanseidov.locationforecast.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeadlineModel {

    @SerializedName("EffectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("EffectiveEpochDate")
    @Expose
    private Integer effectiveEpochDate;
    @SerializedName("Severity")
    @Expose
    private Integer severity;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("EndEpochDate")
    @Expose
    private Integer endEpochDate;
    @SerializedName("MobileLink")
    @Expose
    private String mobileLink;
    @SerializedName("Link")
    @Expose
    private String link;


    public String getEffectiveDate() {
        return effectiveDate;
    }

    public Integer getEffectiveEpochDate() {
        return effectiveEpochDate;
    }

    public Integer getSeverity() {
        return severity;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public String getEndDate() {
        return endDate;
    }

    public Integer getEndEpochDate() {
        return endEpochDate;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public String getLink() {
        return link;
    }
}
