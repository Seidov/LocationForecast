package com.sultanseidov.locationforecast.repository.service.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sultanseidov.locationforecast.repository.model.AdministrativeAreaModel;
import com.sultanseidov.locationforecast.repository.model.CountryModel;
import com.sultanseidov.locationforecast.repository.model.GeoPositionModel;
import com.sultanseidov.locationforecast.repository.model.RegionModel;
import com.sultanseidov.locationforecast.repository.model.SupplementalAdminAreaModel;

import java.util.List;

public class ResGeopositionSearchModel {
    @SerializedName("Version")
    @Expose
    private Integer version;
    @SerializedName("Key")
    @Expose
    private String key;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Rank")
    @Expose
    private Integer rank;
    @SerializedName("LocalizedName")
    @Expose
    private String localizedName;
    @SerializedName("EnglishName")
    @Expose
    private String englishName;
    @SerializedName("PrimaryPostalCode")
    @Expose
    private String primaryPostalCode;
    @SerializedName("Region")
    @Expose
    private RegionModel region;
    @SerializedName("Country")
    @Expose
    private CountryModel country;
    @SerializedName("AdministrativeArea")
    @Expose
    private AdministrativeAreaModel administrativeArea;

    @SerializedName("GeoPosition")
    @Expose
    private GeoPositionModel geoPosition;
    @SerializedName("IsAlias")
    @Expose
    private Boolean isAlias;
    @SerializedName("SupplementalAdminAreas")
    @Expose
    private List<SupplementalAdminAreaModel> supplementalAdminAreas = null;
    @SerializedName("DataSets")
    @Expose
    private List<String> dataSets = null;

    public Integer getVersion() {
        return version;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public Integer getRank() {
        return rank;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getPrimaryPostalCode() {
        return primaryPostalCode;
    }

    public RegionModel getRegion() {
        return region;
    }

    public CountryModel getCountry() {
        return country;
    }

    public AdministrativeAreaModel getAdministrativeArea() {
        return administrativeArea;
    }

    public GeoPositionModel getGeoPosition() {
        return geoPosition;
    }

    public Boolean getAlias() {
        return isAlias;
    }

    public List<SupplementalAdminAreaModel> getSupplementalAdminAreas() {
        return supplementalAdminAreas;
    }

    public List<String> getDataSets() {
        return dataSets;
    }
}
