package com.sultanseidov.locationforecast.repository.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutoCompleteSerachModel implements Parcelable {

    private int id;

    @SerializedName("Key")
    @Expose
    private String key;

    @SerializedName("LocalizedName")
    @Expose
    private String localizedName;

    @SerializedName("country")
    @Expose
    private CountryModel country;

    public AutoCompleteSerachModel() {
    }

    public AutoCompleteSerachModel(int id, String key, String localizedName, CountryModel country) {
        this.id = id;
        this.key = key;
        this.localizedName = localizedName;
        this.country = country;
    }

    protected AutoCompleteSerachModel(Parcel in) {
        id = in.readInt();
        key = in.readString();
        localizedName = in.readString();
    }

    public static final Creator<AutoCompleteSerachModel> CREATOR = new Creator<AutoCompleteSerachModel>() {
        @Override
        public AutoCompleteSerachModel createFromParcel(Parcel in) {
            return new AutoCompleteSerachModel(in);
        }

        @Override
        public AutoCompleteSerachModel[] newArray(int size) {
            return new AutoCompleteSerachModel[size];
        }
    };

    public void setKey(String key) {
        this.key = key;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getKey() {
        return key;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountryModel getCountry() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(key);
        parcel.writeString(localizedName);
    }
}
