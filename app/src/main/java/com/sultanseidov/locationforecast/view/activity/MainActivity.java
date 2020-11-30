package com.sultanseidov.locationforecast.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sultanseidov.locationforecast.R;
import com.sultanseidov.locationforecast.Util.Utils;
import com.sultanseidov.locationforecast.view.fragment.CurrentLocationFragment;

public class MainActivity extends AppCompatActivity {
    public static String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Utils.permissionRequestCode1) {
            if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSION successful");
                CurrentLocationFragment.getCurrentLocationAndRequestApi();
            } else {
                Log.e(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSSION  failed");
                Toast.makeText(getApplicationContext(), "NEED LOCATION PERMISSION", Toast.LENGTH_SHORT).show();

            }
        }

        if (requestCode == Utils.permissionRequestCode2) {
            if (grantResults.length > 2 && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: apply ACCESS_BACKGROUND_LOCATION successful");
                CurrentLocationFragment.getCurrentLocationAndRequestApi();
            } else {
                Log.e(TAG, "onRequestPermissionsResult: apply ACCESS_BACKGROUND_LOCATION  failed");
                Toast.makeText(getApplicationContext(), "NEED LOCATION PERMISSION", Toast.LENGTH_SHORT).show();
            }
        }
    }
}