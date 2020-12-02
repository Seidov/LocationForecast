package com.sultanseidov.locationforecast.view.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.sultanseidov.locationforecast.R;
import com.sultanseidov.locationforecast.repository.model.AutoCompleteSerachModel;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter  extends RecyclerView.Adapter<AutoCompleteAdapter.MyViewHolder> {

    List<AutoCompleteSerachModel> autoCompleteSerachModelList=new ArrayList<>();

    public AutoCompleteAdapter(List<AutoCompleteSerachModel> autoCompleteSerachModelList) {
        this.autoCompleteSerachModelList = autoCompleteSerachModelList;
    }

    public void updateAutoCompleteSearchData(List<AutoCompleteSerachModel> newAutoCompleteSerachModels){
        autoCompleteSerachModelList.clear();
        autoCompleteSerachModelList.addAll(newAutoCompleteSerachModels);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auto_complete_search_model, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.cityName.setText(autoCompleteSerachModelList.get(position).getLocalizedName());
        //holder.countryName.setText(autoCompleteSerachModelList.get(position).getCountry().getLocalizedName());
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(holder.itemView.getContext(), ""+position, Toast.LENGTH_SHORT).show();

            //Intent intent = new Intent(holder.itemView.getContext(), DetailForecastActivity.class);
            //intent.putExtra("AutoCompleteSearch",autoCompleteSerachModelList.get(position));
            //holder.itemView.getContext().startActivity(intent);


            Bundle bundle=new Bundle();
            bundle.putString("cityKey",autoCompleteSerachModelList.get(position).getKey());
            Navigation.findNavController(view).navigate(R.id.action_autoCompleteSearchFragment_to_detailForecastFragment,bundle);

        });
    }

    @Override
    public int getItemCount() {
        return autoCompleteSerachModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        TextView countryName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName=itemView.findViewById(R.id.cityName);
            countryName=itemView.findViewById(R.id.countryName);
        }
    }
}
