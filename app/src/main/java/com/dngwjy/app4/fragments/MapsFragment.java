package com.dngwjy.app4.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.adapters.MainAdapter;
import com.dngwjy.app4.data.models.MasjidModel;
import com.dngwjy.app4.presenters.MapsPresenter;
import com.dngwjy.app4.utils.SetUpLayMan;
import com.dngwjy.app4.views.MapsView;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapsFragment extends Fragment implements MapsView {
    private SwipeRefreshLayout layout;
    private MapView mapView;
    private IMapController mapController;
    private MyLocationNewOverlay myLocationNewOverlay;
    private View rootView;
    private LocationListener listener;
    private LocationManager manager;
    public GeoPoint curLoc;
    private List<MasjidModel> models = new ArrayList<>();
    public boolean centered = false;
    private MapsPresenter presenter;
    private MainAdapter adapter;
    private RecyclerView recyclerView;

    public static Fragment newInstance() {
        MapsFragment fragment = new MapsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.maps_fragment, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout = view.findViewById(R.id.swiper);
        presenter = new MapsPresenter(this, this.getContext());
        adapter = new MainAdapter(getContext(), models);
        setMap(view);
        setMyLocationNewOverlay();
        setRecyclerView(view);
        ActivityCompat.requestPermissions((Activity) Objects.requireNonNull(this.getContext()),new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        getLocation();
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("EventFrag", "onRefresh: "+curLoc.getLatitude()+", "+curLoc.getLongitude());
                presenter.getMasjidTerdekat(curLoc.getLatitude(),curLoc.getLongitude());
            }
        });
    }

    void setRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recMaps);
        recyclerView.setLayoutManager(SetUpLayMan.linearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }


    void setMap(View view) {
        mapView = view.findViewById(R.id.maps);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        mapView.setBuiltInZoomControls(false);
        mapController = mapView.getController();
        mapController.setZoom(18);
    }

    void setMyLocationNewOverlay() {
        myLocationNewOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(Objects.requireNonNull(getContext())), mapView);
        myLocationNewOverlay.enableMyLocation();
        myLocationNewOverlay.getMyLocation();
        mapView.getOverlays().add(myLocationNewOverlay);
        LoadingData();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Log.d("MapsFragment", "onRequestPermissionsResult: grant");
                getLocation();

            }else{
                Log.d("MapsFragment", "onRequestPermissionsResult: denied");
            }
        }

    }

    void getLocation() {
        Log.d("loc", "getLocation: after permission");
        listener = new LocationListenering(getContext());
        manager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        int i = getContext().getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", getContext().getPackageName());
        int j = getContext().getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", getContext().getPackageName());
        if (i == PackageManager.PERMISSION_GRANTED && j == PackageManager.PERMISSION_GRANTED) {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,listener);
            Location loc = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Log.d("loc", "getLocation: " + loc);
            if (loc != null) {
                curLoc = new GeoPoint(loc.getLatitude(), loc.getLongitude());
                if (!centered) {
                    setCentered(curLoc);
                }
                Log.d("curloc ", "getLocation: " + curLoc);
            }
        }

    }

    void setCentered(GeoPoint loc) {
        mapController.setCenter(new GeoPoint(loc.getLatitude(), loc.getLongitude()));
        presenter.getMasjidTerdekat(curLoc.getLatitude(),curLoc.getLongitude());
        centered = true;
    }

    @Override
    public void LoadingData() {
        layout.setRefreshing(true);
        //getLocation();
    }

    @Override
    public void shoLoad() {
        layout.setRefreshing(true);
    }

    @Override
    public void finishLoadin() {
        layout.setRefreshing(false);
    }

    @Override
    public void showData(List<MasjidModel> data) {
        float pk = (float) (180.f/Math.PI);
        Log.d("MapFragment", "showData: "+pk);
        models.clear();
        models.addAll(data);
        adapter.notifyDataSetChanged();
        mapView.getOverlays().clear();
        mapView.invalidate();
        for (int i = 0; i < data.size(); i++) {
            ArrayList col= new ArrayList();
            Marker marker = new Marker(mapView);
            marker.setPosition(new GeoPoint(data.get(i).getLatitude(), data.get(i).getLongitude()));
            Location start=new Location("pointA");
            start.setLatitude(curLoc.getLatitude());
            start.setLongitude(curLoc.getLongitude());
            Location end= new Location("pointB");
            end.setLatitude(data.get(i).getLatitude());
            end.setLongitude(data.get(i).getLongitude());
            float dist=start.distanceTo(end);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            mapView.getOverlays().add(marker);
            marker.setTitle(data.get(i).getNama_masjid());
            marker.setSubDescription(data.get(i).getAlamat()+"\n jarak "+dist+" Meter");
        }
    }


    public class LocationListenering implements LocationListener {
        Context context;

        public LocationListenering(Context context) {
            this.context = context;
        }

        @Override
        public void onLocationChanged(Location location) {
            curLoc = new GeoPoint(location.getLatitude(), location.getLongitude());
            if (!centered) {
                setCentered(curLoc);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            getLocation();
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("GPS Settings");
            builder.setMessage("GPS Tidak Aktiv, Aplikasi ini Butuh GPS yang Aktiv");
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
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
