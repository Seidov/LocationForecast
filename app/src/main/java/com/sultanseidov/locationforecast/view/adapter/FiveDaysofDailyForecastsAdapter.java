package com.sultanseidov.locationforecast.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sultanseidov.locationforecast.R;
import com.sultanseidov.locationforecast.Util.Utils;
import com.sultanseidov.locationforecast.repository.model.DailyForecastModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FiveDaysofDailyForecastsAdapter extends RecyclerView.Adapter<FiveDaysofDailyForecastsAdapter.MyViewHolder> {
    List<DailyForecastModel> dailyForecastsData = new ArrayList<>();

    public FiveDaysofDailyForecastsAdapter(List<DailyForecastModel> dailyForecastsData) {
        this.dailyForecastsData = dailyForecastsData;
    }

    public void updateFiveDaysofDailyForecastsAdapter(List<DailyForecastModel> newDailyForecastsData){
        this.dailyForecastsData.clear();
        newDailyForecastsData.remove(0);
        this.dailyForecastsData.addAll(newDailyForecastsData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_days_of_daily_forecast, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.textDayofDayName.setText(Utils.getDayNameByEpochDate(dailyForecastsData.get(position).getEpochDate()));
            holder.textDayofDayPrecipitationType.setText(dailyForecastsData.get(position).getDay().getIconPhrase());
            holder.textDayofDayTemperature.setText(Utils.getCelsiusByFahrenheit(dailyForecastsData.get(position).getTemperature().getMinimum().getValue(),dailyForecastsData.get(position).getTemperature().getMaximum().getValue()));
            holder.imageViewDayofDayImage.setImageResource(Utils.getIconByIconId(dailyForecastsData.get(position).getDay().getIcon()));

    }

    @Override
    public int getItemCount() {
        return dailyForecastsData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textDayofDayName;
        ImageView imageViewDayofDayImage;
        TextView textDayofDayPrecipitationType;
        TextView textDayofDayTemperature;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             textDayofDayName=itemView.findViewById(R.id.textDayofDayName);
             imageViewDayofDayImage=itemView.findViewById(R.id.imageViewDayofDayImage);
             textDayofDayPrecipitationType=itemView.findViewById(R.id.textDayofDayPrecipitationType);
             textDayofDayTemperature=itemView.findViewById(R.id.textDayofDayTemperature);
        }

    }
}
