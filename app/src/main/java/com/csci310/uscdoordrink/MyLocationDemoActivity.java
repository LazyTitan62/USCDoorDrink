package com.csci310.uscdoordrink;//package com.csci310.uscdoordrink;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;

import com.csci310.uscdoordrink.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnPolylineClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

// Implement OnMapReadyCallback.
public class MyLocationDemoActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean permissionDenied = false;


    private GoogleMap map;

//    private final LatLng village=new LatLng(34.0202,-118.2837);
//    private Marker markervillage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout file as the content view.
        setContentView(R.layout.activity_my_location_demo);
        Intent intent=getIntent();
        // Get a handle to the fragment and register the callback.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // Get a handle to the GoogleMap object and display marker.

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Set the map coordinates to USC.
        // Set the map coordinates to Kyoto Japan.
        map=googleMap;
//        LatLng kyoto = new LatLng(34.0224,  -118.2851);
//        map.moveCamera(CameraUpdateFactory.newLatLng(kyoto));
//        Location myLocation = googleMap.getMyLocation();
//        LatLng myLatLng = new LatLng(myLocation.getLatitude(),
//                myLocation.getLongitude());
        //map.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                myLocation.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
//        // Set the map type to Hybrid.
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Add a marker on the map coordinates.
//        String snippet="fuck";
//        map.addMarker(new MarkerOptions()
//                .position(kyoto)
//                .title("Kyoto"));
//        LatLng village=new LatLng(34.0202,-118.2837);
//        markervillage=map.addMarker(new MarkerOptions().position(village).title("village").snippet(snippet));
//        markervillage.setTag(0);
//        // Move the camera to the map coordinates and zoom in closer.
//        map.moveCamera(CameraUpdateFactory.newLatLng(kyoto));
//        map.moveCamera(CameraUpdateFactory.newLatLng(kyoto));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));
        // Display traffic.
        map.setTrafficEnabled(true);

        // Move the camera to the map coordinates and zoom in closer.
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(usc));
//        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
//        // Display traffic.
//        googleMap.setTrafficEnabled(true);
//        googleMap.setMinZoomPreference(6.0f);
//        googleMap.setMaxZoomPreference(14.0f);


        //set route
//        Polyline polyline1 = map.addPolyline(new PolylineOptions()
//                .clickable(true)
//                .add(
//                        kyoto,
//                        village));
//        polyline1.setColor(
//                0xffFFFF00);
//        polyline1.setWidth(12);
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        enableMyLocation();

//        markervillage=map.addMarker(new MarkerOptions().position(village).title("village"));
//        markervillage.setTag(0);

        map.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        //map.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));
//        // Retrieve the data from the marker.
//        Integer clickCount = (Integer) marker.getTag();
//
//        // Check if a click count was set, then display the click count.
//        if (clickCount != null) {
//            clickCount = clickCount + 1;
//            marker.setTag(clickCount);
//            Toast.makeText(this,
//                    marker.getTitle() +
//                            " has been clicked " + clickCount + " times.",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        // Return false to indicate that we have not consumed the event and that we wish
//        // for the default behavior to occur (which is for the camera to move such that the
//        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }



    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST_CODE);
        }
    }



    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
