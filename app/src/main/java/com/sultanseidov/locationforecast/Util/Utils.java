package com.sultanseidov.locationforecast.Util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

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

}
