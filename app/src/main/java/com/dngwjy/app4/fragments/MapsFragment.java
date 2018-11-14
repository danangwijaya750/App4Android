package com.dngwjy.app4.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.models.MasjidModel;
import com.dngwjy.app4.presenters.MapsPresenter;


import org.osmdroid.api.IMapController;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.List;
import java.util.Objects;

public class MapsFragment extends Fragment implements MapsPresenter {
    private MapView mapView;
    private IMapController mapController;
    private MyLocationNewOverlay myLocationNewOverlay;
    private View rootView;
    private LocationListener listener;
    private LocationManager manager;
    public GeoPoint curLoc;
    private  List<MasjidModel> models;
    public boolean centered = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.maps_fragment, container, false);
//        setMap(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setMap(view);
        setMyLocationNewOverlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    void setMap(View view) {
        mapView = view.findViewById(R.id.maps);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        mapView.setBuiltInZoomControls(false);
        mapController = mapView.getController();
        mapController.setZoom(13);
    }

    void setMyLocationNewOverlay() {
        myLocationNewOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), mapView);
        myLocationNewOverlay.enableMyLocation();
        mapView.getOverlays().add(myLocationNewOverlay);
        LoadingData();
    }

    void getLocation(){
        listener= new LocationListenering(getContext());
        manager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        int i = getContext().getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", getContext().getPackageName());
        int j = getContext().getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", getContext().getPackageName());
        if(i == PackageManager.PERMISSION_GRANTED && j == PackageManager.PERMISSION_GRANTED){

        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
        Location loc=manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d("loc", "getLocation: " +loc);
        if(loc!=null){
            curLoc= new GeoPoint(loc.getLatitude(),loc.getLongitude());

            if(!centered){
                setCentered(curLoc);
                showData(models);
            }
            Log.d("curloc ", "getLocation: "+curLoc);
        }
    }
    void setCentered(GeoPoint loc){
        mapController.setCenter(new GeoPoint(loc.getLatitude(),loc.getLongitude()));
        centered=true;

    }

    @Override
    public void LoadingData() {
        getLocation();

    }

    @Override
    public void shoLoad() {

    }

    @Override
    public void finishLoadin() {

    }

    @Override
    public void showData(List<MasjidModel> data) {
        double longi[]={110.3885578,110.3874904,110.3859055};
        double lat[]={-7.7729103,-7.771337,-7.7740175};
        String name[]={"Majid Alfalaah","Mushola AlMusthofa","Masjid Mujahidin UNY"};
        for (int i = 0; i < longi.length; i++) {
            Marker marker= new Marker(mapView);
            marker.setPosition(new GeoPoint(lat[i],longi[i]));
            marker.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_BOTTOM);
            mapView.getOverlays().add(marker);
            marker.setTitle(name[i]);
        }
    }


    public class LocationListenering implements LocationListener {
        Context context;

        public LocationListenering(Context context) {
            this.context = context;
        }

        @Override
        public void onLocationChanged(Location location) {
            curLoc= new GeoPoint(location.getLatitude(),location.getLongitude());
            if(!centered){
                setCentered(curLoc);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            final AlertDialog.Builder builder= new AlertDialog.Builder(context);
            builder.setTitle("GPS Settings");
            builder.setMessage("GPS Tidak Aktiv, Aplikasi ini Butuh GPS yang Aktiv");
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(intent);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            builder.show();
        }
    }

}
