package com.sultanseidov.locationforecast.repository.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sultanseidov.locationforecast.repository.model.AutoCompleteSerachModel;

@Database(entities = {AutoCompleteSerachModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IAutoCompleteSearchDao autoCompleteSearchDao();
}
