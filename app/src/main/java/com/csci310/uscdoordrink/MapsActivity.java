package com.csci310.uscdoordrink;

//public class MapsActivity {
//}
//public class MapsActivity {
//}

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Implement OnMapReadyCallback.

public class MapsActivity extends AppCompatActivity
        implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        TaskLoadedCallback{

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
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final float DEFAULT_ZOOM = 15f;

    private GoogleMap map;
    Location currentLocation;
    MarkerOptions place1, place2;
    //Button getDirection;
    String driving_duration = null;
    int drivingint = 60;

    String RecommendMerchant=null;
    String customerName="Den";


    private Polyline currentPolyline;
    List<MarkerOptions>markerOptionList=new ArrayList<MarkerOptions>();
    //online
    //private Polyline mPolyline;

    //private final LatLng village = new LatLng(34.0202, -118.2837);
    //private Marker markervillage;
    //LocationManager mLocationManager;
    int isMerchant;
    String userID;
    //permission
    private static final String TAG = "MapActivity";
    List<HashMap<String,String>> allResult=new ArrayList<HashMap<String,String>>();
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionsGranted = false;

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapsActivity.this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout file as the content view.
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        initMap();
        getLocationPermission();

        //--------------
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    RecommendationQuery r=new RecommendationQuery();
                    RecommendMerchant=r.getMerchant(customerName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Background work here

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //System.out.println("RecommendMerchant"+RecommendMerchant);
                        if(RecommendMerchant!=null)
                        {
                            TextView thetext = (TextView)findViewById(R.id.recommendation);
                            //System.out.println ("HBO");
                            thetext.setText("Daily Recommendation: "+RecommendMerchant);
                            //below is toast
                            String text="Daily Recommendation: "+RecommendMerchant;
                            Context context = getApplicationContext();
                            Toast toast=Toast.makeText(context, text, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }
        });



        //buttons----------------------------------------------------------
        Button btn_show_map= findViewById(R.id.button_map);
        Button btn_show_orderHis= findViewById(R.id.button_order_history);
        Button btn_show_profile= findViewById(R.id.button_profile);

        //receive info from intent----------------------------------
        userID=intent.getExtras().getString("userID");
        isMerchant=intent.getExtras().getInt("isMerchant");
        customerName=userID;

        if(isMerchant==1){
            btn_show_profile.setEnabled(false);
        }
        //--------------------------------------------
        btn_show_map.setOnClickListener(v->{
            Intent ID = new Intent(MapsActivity.this, MapsActivity.class);
            ID.putExtra("userID", userID);
            ID.putExtra("isMerchant", isMerchant);
            startActivity(ID);
        });

        btn_show_orderHis.setOnClickListener(v->{
            Intent ID = new Intent(MapsActivity.this, OrderActivity.class);
            ID.putExtra("userID", userID);
            ID.putExtra("isMerchant", isMerchant);
            startActivity(ID);
        });

        btn_show_profile.setOnClickListener(v->{
            Intent ID = new Intent(MapsActivity.this, AnalysisActivity.class);
            ID.putExtra("userID", userID);
            startActivity(ID);
        });

        //--------------------------------------------


//        if (mLocationPermissionsGranted) {
//            getDeviceLocation();
//
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//        }
    }





    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        try {

            if (mLocationPermissionsGranted) {

                mFusedLocationProviderClient.getLastLocation()
                        .addOnSuccessListener(this, location -> {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                currentLocation = location;

                                try {

                                    moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                            DEFAULT_ZOOM);
                                }
                                catch(IllegalStateException e)
                                {
                                    LatLng village=new LatLng(34.0202,-118.2837);
                                    moveCamera(village,15f);
                                }
                                catch(Exception e)
                                {
                                    LatLng village=new LatLng(34.0202,-118.2837);
                                    moveCamera(village,15f);
                                }
                                getMerchantFromDataBase();



                            } else {
                                Log.d(TAG, "onComplete: current location is null");
                                Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_LONG).show();
                            }
                        });
            }
            //                @SuppressLint("MissingPermission")
            //                final Task location = mFusedLocationProviderClient.getLastLocation();
            //                location.addOnCompleteListener(new OnCompleteListener() {
            //                    @Override
            //                    public void onComplete(@NonNull Task task) {
            //                        if(task.isSuccessful()&& task.getResult() != null){
            //                            Log.d(TAG, "onComplete: found location!");
            //                                currentLocation = (Location) task.getResult();
            //
            //                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
            //                                    DEFAULT_ZOOM
            //                                    );
            //
            //                        }
            //                        else{
            //                            Log.d(TAG, "onComplete: current location is null");
            //                            Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
            //                        }
            //                    }
            //                });


        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }

    }

    private void moveCamera(LatLng latLng, float zoom) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }
    // Get a handle to the GoogleMap object and display marker.

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        map=googleMap;

        //System.out.println("Fucl");
        //addMarker();
//        map.addMarker(place1);
//        map.addMarker(place2);
        // Set the map coordinates to USC.
        // Set the map coordinates to Kyoto Japan.
        //map = googleMap;
        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            map.setMyLocationEnabled(true);
            map.setOnMyLocationButtonClickListener(this);
            map.setOnMyLocationClickListener(this);
            map.setOnInfoWindowClickListener(this);



            //}
//        map.addMarker(place1);
//        map.addMarker(place2);
            map.setOnMarkerClickListener(this);
//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        //Location myLocation= LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//        myLatLng = new LatLng(myLocation.getLatitude(),
//                myLocation.getLongitude());
//        map.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
//        // Set the map type to Hybrid.
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            // Add a marker on the map coordinates.
//        String snippet = "";
////        map.addMarker(new MarkerOptions()
////                .position(kyoto)
////                .title("Kyoto"));
//        LatLng village = new LatLng(34.0202, -118.2837);
//        markervillage = map.addMarker(new MarkerOptions().position(village).title("village").snippet(snippet));
//        markervillage.setTag(0);
//        map.moveCamera(CameraUpdateFactory.zoomTo(14));
            // Display traffic.
            map.setTrafficEnabled(true);

        }
//        map.setOnMyLocationButtonClickListener(this);
//        map.setOnMyLocationClickListener(this);
//        enableMyLocation();
        //String data;
//        String url_line=getUrl(village, myLatLng, "driving");
//        FetchURL fetchDistanceTime= (FetchURL)new FetchURL(MapsActivity.this).execute(url_line, "driving");

        //        try {
//            String data=downloadUrl("https://maps.googleapis.com/maps/api/directions/json?origin=34.0202,-118.2837&destination=34.02171833333333,-118.28279&mode=driving&key=AIzaSyAPLYER1TPpF9RSypNubp_yz6TzCG5hogk");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //String response=fetchDistanceTime.getRes();
////        System.out.println("theresponse "+response);

    }



    /**
     * Called when the user clicks a marker.
     */
    @Override
    public boolean onMarkerClick( Marker marker) {
        //
        // System.out.println("currentlocationIs: "+currentLocation.getLongitude()+" "+currentLocation.getLatitude());
        if(currentLocation!=null) {
            LatLng mylat = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            String url_line = getUrl(marker.getPosition(), mylat, "driving");
            try {
                new FetchURL(MapsActivity.this).execute(url_line, "driving");
            } catch (Exception e) {
                e.printStackTrace();

            }
            //test
            final String[] driving = new String[1];
            final Object[] walking = new Object[1];

            String url_driving = getUrl(marker.getPosition(), mylat, "driving");
            String url_walking = getUrl(marker.getPosition(), mylat, "walking");
            Thread thread1 = new Thread(() -> {

                driving[0] = getDuration(url_driving);
            });

            Thread thread2 = new Thread(() -> {
                walking[0] = getDuration(url_walking);
            });

            thread1.start();
            thread2.start();





            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            driving_duration=driving[0];
            System.out.println("passed duration");
            System.out.println(driving_duration);
            if(driving_duration!=null&&driving_duration.length()!=0)
            {
                if(driving_duration.contains("min"))
                {
                    String[]thesplit=driving_duration.split(" ");
                    drivingint=Integer.parseInt(thesplit[0]);
                }
                else
                {
                    drivingint=60;
                }
            }
            else
            {
                drivingint=60;
            }



            if(driving==null||driving[0]==null)
            {
                driving[0]="unknown";
            }
            if(walking==null||walking[0]==null)
            {
                walking[0]="unknown";
            }

            String snippet = "driving: " + driving[0] + "\n" + "walking: " + walking[0];
            marker.setSnippet(snippet);

            map.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));
        }
        else
        {
            Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


//    @SuppressLint("MissingPermission")
//    private void enableMyLocation() {
//        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            if (map != null) {
//                map.setMyLocationEnabled(true);
//            }
//        } else {
//            // Permission to access the location is missing. Show rationale and request permission
//            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
//        }
//    }


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


//    private Location getLastKnownLocation() {
//        mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
//        List<String> providers = mLocationManager.getProviders(true);
//        Location bestLocation = null;
//        for (String provider : providers) {
//            @SuppressLint("MissingPermission") Location l = mLocationManager.getLastKnownLocation(provider);
//            if (l == null) {
//                continue;
//            }
//            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
//                // Found best last known location: %s", l);
//                bestLocation = l;
//            }
//        }
//        return bestLocation;
//    }


    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" +getString(R.string.map_api);
        //String url="https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyAPLYER1TPpF9RSypNubp_yz6TzCG5hogk";

        return url;
    }



    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = map.addPolyline((PolylineOptions) values[0]);
    }

    public String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);
            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();
            // Connecting to url
            urlConnection.connect();
            // Reading data from url
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            Log.d("mylog", "Downloaded URL: " + data.toString());
            br.close();
        } catch (Exception e) {
            Log.d("mylog", "Exception downloading URL: " + e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    public String getDuration(String theurl)
    {
        String duration=null;
        String a=null;

        try {

            a=downloadUrl(theurl);
            System.out.println("downloadUrl: "+a);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsnobject=null;
        try {
            jsnobject = new JSONObject(a);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

        try {
            duration = jsnobject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("duration").getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return duration;
    }

    public void getMerchantFromDataBase()
    {
//        new Thread(()->{
//            c
//            ConvertToMarker();
//            addMarker();
//    }).start();


        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler;
        handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    insertDaniel in=new insertDaniel();
                    allResult =in.doInBackground();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Background work here

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        ConvertToMarker();
                        addMarker();
                    }
                });
            }
        });

    }

    public void ConvertToMarker()
    {
        for(int i=0;i<allResult.size();i++)
        {
            String name=allResult.get(i).get("username");
            String latstring=allResult.get(i).get("latitude");
            float lat=Float.parseFloat(latstring);

            String lonstring=allResult.get(i).get("lon");
            float lon=Float.parseFloat(lonstring);
            MarkerOptions op=new MarkerOptions().position(new LatLng(lat, lon)).title(name).snippet("info");
            markerOptionList.add(op);
        }
    }

    public void addMarker()
    {

        for(int i=0;i<markerOptionList.size();i++)
        {
            System.out.println("markerhere "+markerOptionList.get(i).getTitle());
            map.addMarker(markerOptionList.get(i));
        }
    }


    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

        if(isMerchant!=1){
            Toast.makeText(this, "Info window clicked",
                    Toast.LENGTH_SHORT).show();
            System.out.println(marker.getTitle());
            //send intent here
            //also send over username
            Intent intent = new Intent(MapsActivity.this, MenuActivity.class);
            intent.putExtra("customerID", userID);
            intent.putExtra("merchantID", marker.getTitle());
            intent.putExtra("NeededTime", drivingint);
            startActivity(intent);
        }

    }
}