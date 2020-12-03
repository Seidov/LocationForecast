package com.sultanseidov.locationforecast.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.sultanseidov.locationforecast.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static int permissionRequestCode1 = 1;
    public static String[] permissionRequest1 = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    public static int permissionRequestCode2 = 2;
    public static String[] permissionRequest2 = {android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            "android.permission.ACCESS_BACKGROUND_LOCATION"};


    public static boolean isGrantLocationPermissions(Activity activity, String[] permissionRequestList, int permissionRequestCode) {
        boolean isPermitGrant = false;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {

            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(permissionRequestList, permissionRequestCode);
                isPermitGrant = true;
            } else {
                isPermitGrant = true;
            }
        } else {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && activity.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(permissionRequestList, permissionRequestCode);
            } else {
                isPermitGrant = true;
            }
        }
        return isPermitGrant;
    }

    public static String getDayNameByEpochDate(int l) {
        Date date = new Date(Long.valueOf(l) * 1000);
        DateFormat format = new SimpleDateFormat("EEEE");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);


        return formatted;

    }

    public static String getDateNameByEpochDate(int l) {
        Date date = new Date(Long.valueOf(l) * 1000);
        DateFormat format = new SimpleDateFormat("E, MMM dd yyyy");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);


        return formatted;

    }

    public static boolean isDay(int l) {

        boolean isDay = false;
        Date date = new Date(Long.valueOf(l) * 1000);
        DateFormat df6 = new SimpleDateFormat("HH");
        String formatted = df6.format(date);
        int hh = Integer.valueOf(formatted);
        if (hh >= 6 && hh <= 18) {
            isDay = true;
        }
        return isDay;

    }

    public static String getCelsiusByFahrenheit(Integer minFahrenheit, Integer maxFahrenheit) {
        Integer fahrenheit = (minFahrenheit + maxFahrenheit) / 2;
        return "" + ((fahrenheit - 32) * 5 / 9);
    }

    public static int getIconByIconId(int iconId) {

        int categoryIconId = 0;

        switch (iconId) {
            case 1:
                categoryIconId = R.drawable.icon1;
                break;
            case 2:
                categoryIconId = R.drawable.icon2;
                break;
            case 3:
                categoryIconId = R.drawable.icon3;
                break;
            case 4:
                categoryIconId = R.drawable.icon4;
                break;
            case 5:
                categoryIconId = R.drawable.icon5;
                break;
            case 6:
                categoryIconId = R.drawable.icon6;
                break;
            case 7:
                categoryIconId = R.drawable.icon7;
                break;
            case 8:
                categoryIconId = R.drawable.icon8;
                break;
            case 11:
                categoryIconId = R.drawable.icon11;
                break;
            case 12:
                categoryIconId = R.drawable.icon12;
                break;
            case 13:
                categoryIconId = R.drawable.icon13;
                break;
            case 14:
                categoryIconId = R.drawable.icon14;
                break;
            case 15:
                categoryIconId = R.drawable.icon15;
                break;
            case 16:
                categoryIconId = R.drawable.icon16;
                break;
            case 417:
                categoryIconId = R.drawable.icon17;
                break;
            case 18:
                categoryIconId = R.drawable.icon18;
                break;
            case 19:
                categoryIconId = R.drawable.icon19;
                break;
            case 20:
                categoryIconId = R.drawable.icon20;
                break;
            case 21:
                categoryIconId = R.drawable.icon21;
                break;
            case 22:
                categoryIconId = R.drawable.icon22;
                break;
            case 23:
                categoryIconId = R.drawable.icon23;
                break;
            case 24:
                categoryIconId = R.drawable.icon24;
                break;
            case 25:
                categoryIconId = R.drawable.icon25;
                break;
            case 26:
                categoryIconId = R.drawable.icon26;
                break;
            case 29:
                categoryIconId = R.drawable.icon29;
                break;
            case 30:
                categoryIconId = R.drawable.icon30;
                break;
            case 31:
                categoryIconId = R.drawable.icon31;
                break;
            case 32:
                categoryIconId = R.drawable.icon32;
                break;
            case 33:
                categoryIconId = R.drawable.icon33;
                break;
            case 34:
                categoryIconId = R.drawable.icon34;
                break;
            case 35:
                categoryIconId = R.drawable.icon35;
                break;
            case 36:
                categoryIconId = R.drawable.icon36;
                break;
            case 37:
                categoryIconId = R.drawable.icon37;
                break;
            case 38:
                categoryIconId = R.drawable.icon38;
                break;
            case 39:
                categoryIconId = R.drawable.icon39;
                break;
            case 40:
                categoryIconId = R.drawable.icon40;
                break;
            case 41:
                categoryIconId = R.drawable.icon41;
                break;
            case 42:
                categoryIconId = R.drawable.icon42;
                break;
            case 43:
                categoryIconId = R.drawable.icon43;
                break;
            case 44:
                categoryIconId = R.drawable.icon44;
                break;

        }
        return categoryIconId;
    }

    public static int getWeatherImageByIconId(int iconId) {

        int categoryIconId = 0;
        int Id = 0;

        if (iconId >= 1 && iconId <= 5) {
            Id = 1;
        } else if (iconId >= 6 && iconId <= 11) {
            Id = 2;
        } else if (iconId >= 12 && iconId <= 21) {
            Id = 3;
        } else if (iconId >= 22 && iconId <= 29) {
            Id = 4;
        }


        switch (Id) {
            case 1:
                categoryIconId = R.drawable.sunny;
                break;
            case 2:
                categoryIconId = R.drawable.cloudy;
                break;
            case 3:
                categoryIconId = R.drawable.rainy;
                break;
            case 4:
                categoryIconId = R.drawable.snow;
                break;
            case 32:
                categoryIconId = R.drawable.windy;
                break;
            case 30:
                categoryIconId = R.drawable.dust;
                break;

        }
        return categoryIconId;
    }

    public static int getImageByDayOrNight(boolean isDay) {

        int imageResource;
        if (isDay) {
            imageResource = R.drawable.day;
        } else {
            imageResource = R.drawable.night;
        }
        return imageResource;
    }

    public static boolean isNetworkAvailable(Context context) {
        // Get Connectivity Manager class object from Systems Service
        ConnectivityManager cm = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get Network Info from connectivity Manager
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
