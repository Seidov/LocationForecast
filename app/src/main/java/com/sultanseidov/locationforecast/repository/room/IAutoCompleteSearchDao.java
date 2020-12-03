package com.sultanseidov.locationforecast.repository.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sultanseidov.locationforecast.repository.model.AutoCompleteSerachModel;

import java.util.List;
@Dao
public interface IAutoCompleteSearchDao {

    @Query("SELECT * FROM autocompleteserachmodel  ORDER BY id desc LIMIT 5")
    List<AutoCompleteSerachModel> getAllautoCompleteSerachModel();

    @Query("SELECT * FROM autocompleteserachmodel where `key` = :cityKey")
    AutoCompleteSerachModel getAllautoCompleteSerachModelById(String cityKey);

    @Insert
    void insert(AutoCompleteSerachModel autoCompleteSerachModel);

    @Delete
    void delete(AutoCompleteSerachModel autoCompleteSerachModel);

    @Update
    void update(AutoCompleteSerachModel autoCompleteSerachModel);
}
