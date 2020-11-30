package com.sultanseidov.locationforecast.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryModel {

    @SerializedName("ID")
    @Expose
    private String countryId;

    @SerializedName("LocalizedName")
    @Expose
    private String countryLocalizedName;

    @SerializedName("EnglishName")
    @Expose
    private String countryEnglishName;


    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryLocalizedName() {
        return countryLocalizedName;
    }

    public void setCountryLocalizedName(String countryLocalizedName) {
        this.countryLocalizedName = countryLocalizedName;
    }

    public String getCountryEnglishName() {
        return countryEnglishName;
    }

    public void setCountryEnglishName(String countryEnglishName) {
        this.countryEnglishName = countryEnglishName;
    }
}
