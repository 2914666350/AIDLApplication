package com.example.locationrecord;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class LocationService extends Service {
    private static final String TAG = "LocationService";
    public LocationService() {
    }

    @Override
    public IBinder onBind(
        return new ILocationBinder.Stub() {
        @Override
        public Location getLocation() throws RemoteException {Intent intent) {
                if(locationManager!=null) {
                        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER))
                    }
                return null;
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: is called");
        setLocationManager();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: is called");
    }
    LocationManager locationManager=null;
    private  void setLocationManager(){
        if(locationManager==null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        != (PackageManager.PERMISSION_GRANTED) &&
                        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0.1f,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            Log.d(TAG, "onLocationChanged: logtitude="
                                    +location.getLongitude()+",Latitude="+location.getLatitude());
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                            Log.d(TAG, "onStatusChanged: ");
                        }

                        @Override
                        public void onProviderEnabled(String s) {
                            Log.d(TAG, "onProviderEnabled: provider="+s);
                        }

                        @Override
                        public void onProviderDisabled(String s) {
                            Log.d(TAG, "onProviderDisabled: provider="+s);
                        }
                    }
            );
        }
    }
}
