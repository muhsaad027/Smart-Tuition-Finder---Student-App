package com.example.saadiqbal.ht_studentmodule;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.saadiqbal.ht_studentmodule.CoursePKG.Course;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.ch;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.saadiqbal.ht_studentmodule.Login.PREF_UNAME;

public class MainAppScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerClickListener {
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    final ArrayList<String> autofillcoursesDB = new ArrayList<String>();
    AutoCompleteTextView autoCompleteTextView;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    public String search_course;
    public String contact;
    private GoogleMap mMap;
    LinearLayout ll_rec , ll_datares;
    public TextView t1, t2, nummm;
    public String phone;
    public Button tutorReq, serach, cencelfragrequest;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    public String Longitude, Latitude;
    private HashMap<Marker, Integer> mHashMap = new HashMap<Marker, Integer>();
    private HashMap<Integer, JSONObject> hashMapTutors = new HashMap<Integer, JSONObject>();
    TextView Num;
    ImageView gototutorhome,calltutorhome;
    SeekBar seekbar;
    String seekbarValue,SturdyType;
    int radius;
    LinearLayout gotoLinear,callLinear;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        autoCompleteTextView = (AutoCompleteTextView)  findViewById(R.id.searchAutoComplete);
            Num = (TextView)findViewById(R.id.textView1);
            seekbar = (SeekBar)findViewById(R.id.seekbar);
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                     seekbarValue = String.valueOf(i);

                    if(i < 3)
                    {
                        CustomizePopUpLayout n = new CustomizePopUpLayout();
                        n.RangeRadius(MainAppScreen.this,"Too much decrease of radius will\nmake it difficult to find\nTUTOR");
                    }
                    if(i > 7)
                    {
                        CustomizePopUpLayout n = new CustomizePopUpLayout();
                        n.RangeRadius(MainAppScreen.this,"Too much increase of radius will\nmake effect the cost of\nTUITION");
                    }

                    Num.setText(seekbarValue);
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        AutoCourseFillData();
        setSupportActionBar(toolbar);

            gotoLinear = (LinearLayout) findViewById(R.id.gotohomeimageLinearLayour);
            callLinear = (LinearLayout)findViewById(R.id.calltohomeimageLinearLayout);
            gototutorhome = (ImageView)findViewById(R.id.gotohomeimage);
            gototutorhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int colorId = R.color.white;
                    if (gotoLinear.getTag() != null)
                        colorId = (int) gotoLinear.getTag();


                    if ( colorId == R.color.lineargreycolor) {
                        gotoLinear.setTag(R.color.white);
                        gotoLinear.setBackgroundColor(getResources().getColor(R.color.white));
                    } else {
                        gotoLinear.setTag(R.color.lineargreycolor);
                        gotoLinear.setBackgroundColor(getResources().getColor(R.color.lineargreycolor));
                    }
                }
            });
            //#FFBEBABA
            calltutorhome = (ImageView)findViewById(R.id.calltohomeimage);
            calltutorhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int colorId = R.color.white;
                    if (callLinear.getTag() != null)
                        colorId = (int) callLinear.getTag();


                    if ( colorId == R.color.lineargreycolor) {
                        callLinear.setTag(R.color.white);
                        callLinear.setBackgroundColor(getResources().getColor(R.color.white));
                    } else {
                        callLinear.setTag(R.color.lineargreycolor);
                        callLinear.setBackgroundColor(getResources().getColor(R.color.lineargreycolor));
                    }
                }
            });
            serach = (Button) findViewById(R.id.search_butons);
        serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (autoCompleteTextView.getText().toString().isEmpty()) {
                    autoCompleteTextView.setError("Coures name is required!");
                    requestFocus(autoCompleteTextView);
                    return;
                }
                if (autoCompleteTextView != null) {
                    CustomizePopUpLayout n = new CustomizePopUpLayout();
                    n.RangeRadius(MainAppScreen.this,"We had just find best \n Tutors for YOU !\n* if not VISIBLE kindly increase\nRADUIS");
                    View view = new View(MainAppScreen.this);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    datasend();
                }

                /*CustomDialogClass cdd=new CustomDialogClass(MainAppScreen.this);
                cdd.show();*/

            }
        });
        nummm = (TextView) findViewById(R.id.phoneNumber);
        t1 = (TextView) findViewById(R.id.tutorviewmain);
        tutorReq = (Button) findViewById(R.id.reqtutor);
        tutorReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCompleteTextView != null) {
   // new AsyncCaller().execute();
                    reqsend();
                }
                if (autoCompleteTextView.getText().toString().isEmpty()) {
                    autoCompleteTextView.setError("You must select the course!");
                    requestFocus(autoCompleteTextView);
                    return;
                }

                //Toast.makeText(getBaseContext(), "Request Cencel",Toast.LENGTH_LONG).show();
               /* Intent intent = new Intent(MainAppScreen.this,RequsetProgress.class);
                startActivity(intent);
                finish();*/
            }
        });
        //t1.setOnClickListener(this);
        // t2.setOnClickListener(this);
        mDrawerList = (ListView) findViewById(R.id.navList1);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        addDrawerItems();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        ll_datares = (LinearLayout)findViewById(R.id.ll_datares);
        ll_rec = (LinearLayout) findViewById(R.id.ll_rec);

        ll_datares.setVisibility(View.GONE);
        ll_rec.setVisibility(View.VISIBLE);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        onNewIntent(getIntent());
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void addDrawerItems() {
        String[] a = {"Short Courses", "1- Photoshop", "2- Language", "3- Networking", "4- Android", "5- Web Development", "6- Database", "7- MS Office", "8- Wordpress"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, a);
        mDrawerList.setAdapter(mAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_app_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.INBOX) {
//            // Handle the camera action
//
//            Intent i = new Intent(this, InboxActivity.class);
//            startActivity(i);}
        if (id == R.id.COURSES) {

            Intent ia = new Intent(this, CurrentTuitons.class);
            startActivity(ia);
}
//else if (id == R.id.FAQS) {
//            Intent iaa = new Intent(this, FAQs.class);
//            startActivity(iaa);

//        }
//        else if (id == R.id.HELP) {


//        }
        else if (id == R.id.SETTINGS) {
            Intent iaa = new Intent(this, Settings.class);
            startActivity(iaa);

        } else if (id == R.id.CONTACT) {
            Intent iaa = new Intent(this, About.class);
            startActivity(iaa);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }
        mMap.setOnMarkerClickListener(this);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        logDebug("OnNewIntent called");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            ll_datares.setVisibility(View.VISIBLE);
            ll_rec.setVisibility(View.GONE);
            contact = bundle.getString("phoneNo");
            String type = bundle.getString("type");
            String name = bundle.getString("title");
            logDebug("Name" + name);
            t1.setText("Status : Available\n" + name + "\nType : " + type);
            nummm.setText(contact);


        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.iamstudentpin));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            //You can add here other case statements according to your requirement.
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tutorviewmain:

                CustomDialogClass cdd1 = new CustomDialogClass(this, new JSONObject());
                cdd1.show();
                break;
        }
    }

    public void logDebug(String message) {
        Log.v("MainAppScreen", "" + message);
    }

    public void datasend() {


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            phone = (String) bundle.get("phonenumber");
        }
        SharedPreferences shared = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        String channel = (shared.getString(Login.PREF_UNAME, ""));
        logDebug("username  " + channel);
        logDebug("courses  " +autoCompleteTextView.getText().toString());
        String temp = autoCompleteTextView.getText().toString();
        logDebug("Latitude:  " + mLastLocation.getLatitude() + "\n Longitutde:  " + mLastLocation.getLongitude());
//        if (phone.length() == 10) {
//            phone = "+92" + phone;
//        } else {
//            phone = "+92" + phone.substring(1);
//        }
        //Toast.makeText(MainAppScreen.this,""+seekbarValue,Toast.LENGTH_LONG).show();
        if(seekbarValue == null )
        {
            seekbarValue = "1";
          //  Toast.makeText(MainAppScreen.this,"if : "+seekbarValue,Toast.LENGTH_LONG).show();
        }
        AndroidNetworking.get(URLStudents.URL_TutorGetInfo)
                .addQueryParameter("studentId", channel)
                .addQueryParameter("longitude", "" + mLastLocation.getLongitude())
                .addQueryParameter("latitude", "" + mLastLocation.getLatitude())
                .addQueryParameter("radius", String.valueOf(Integer.parseInt(seekbarValue)))
                .addQueryParameter("courses", "" + autoCompleteTextView.getText().toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boolean error = false;
                        String message = "";
logDebug("Response :  "+response);
                        try {
                            logDebug("Response  :  " + response);
                            JSONArray tutorArray = response.getJSONArray("result");

                            for (int i = 0; i < tutorArray.length(); i++) {
                                JSONObject tutor = tutorArray.getJSONObject(i);
                                double longitude = tutor.getDouble("Longitude");
                                double latitude = tutor.getDouble("Latitude");
                                String name = tutor.getString("TutName");
                                String TutPhone = tutor.getString("TutPhone");
                                String Teachingtypes =tutor.getString("TeachingType");
                                String TeacherQualif =tutor.getString("TutQual");
                                LatLng latLng = new LatLng(latitude, longitude);
                                MarkerOptions markerOptions = new MarkerOptions();

                                markerOptions.position(latLng);
                                markerOptions.title("" + name);
                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.tutorpin));
                                markerOptions.snippet(TeacherQualif);

                                Marker marker = mMap.addMarker(markerOptions);

                                hashMapTutors.put(i, tutor);
                                mHashMap.put(marker, i);
                            }
                            ///  longitude
                            //
                            // message = response.getString("message");
                            //   error = response.getBoolean("error");
                        } catch (JSONException e) {
                            logDebug("Error " + e.getLocalizedMessage());
                        }


                      /*  if(!error)
                        {
                            Toast.makeText(MainAppScreen.this,""+phone,Toast.LENGTH_LONG).show();
                            Toast.makeText(MainAppScreen.this,""+message,Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(MainAppScreen.this,""+phone,Toast.LENGTH_LONG).show();
                            Toast.makeText(MainAppScreen.this,""+message,Toast.LENGTH_LONG).show();
                        }*/
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        logDebug("Error   " + error.getLocalizedMessage());

                    }
                });
    }

    /////////////////ReqDataSend////////////////////////
    public void reqsend() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(gotoLinear.getTag() != null &&(int) gotoLinear.getTag() == R.color.lineargreycolor && callLinear.getTag() != null &&(int)  callLinear.getTag() == R.color.lineargreycolor){
            SturdyType = "BOTH";
        }
        else if(gotoLinear.getTag() != null &&(int)  gotoLinear.getTag()==R.color.lineargreycolor)
        {
            SturdyType = "TUTOR LOCATION";
        }
        else if(callLinear.getTag() != null &&(int)  callLinear.getTag()==R.color.lineargreycolor)
        {
            SturdyType = "STUDENT LOCATION";
        }else {
            SturdyType = "BOTH";
        }
        if (bundle != null) {
            phone = (String) bundle.get("phonenumber");
        }
        SharedPreferences shared = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        String channel = (shared.getString(Login.PREF_UNAME, ""));

        if(seekbarValue == null )
        {
            seekbarValue = "1";
            //  Toast.makeText(MainAppScreen.this,"if : "+seekbarValue,Toast.LENGTH_LONG).show();
        }

        logDebug("username  " + channel);
        logDebug("courses  " + autoCompleteTextView.getText().toString());
        logDebug("Latitude:  " + mLastLocation.getLatitude() + "\n Longitutde:  " + mLastLocation.getLongitude());
//        if (phone.length() == 10) {
//            phone = "+92" + phone;
//        } else {
//            phone = "+92" + phone.substring(1);
//        }
        logDebug("StdId :  " + channel + "     " + mLastLocation.getLongitude() + "     " + mLastLocation.getLatitude() + "   " + autoCompleteTextView.getText().toString());
        AndroidNetworking.get(URLStudents.URL_SendRequest2Tutor)
                .addQueryParameter("studentId", channel)
                .addQueryParameter("longitude", "" + mLastLocation.getLongitude())
                .addQueryParameter("latitude", "" + mLastLocation.getLatitude())
                .addQueryParameter("courses", autoCompleteTextView.getText().toString())
                .addQueryParameter("radius", String.valueOf(Integer.parseInt(seekbarValue)))
                .addQueryParameter("checktuition", ""+SturdyType)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boolean error = false;
                        String message = "";
                        String reqID = "";




                        try {
                            JSONArray tutorArray = response.getJSONArray("result");
                            JSONObject tutorObject = tutorArray.getJSONObject(0);

                            reqID = tutorObject.getString("requestId");
                        if(reqID != null)
                        {

                        }
                            // reqID
                            //
                            logDebug("Response  :  " + response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if (!reqID.equals("")) {
                            RequestFragment rf = new RequestFragment();
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();

                            Bundle args = new Bundle();
                            args.putString("reqID",reqID);
                            rf.setArguments(args);
                            ft.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
                            ft.replace(R.id.mainappcontainer, rf);


                            ft.commit();
                        } else {
                            Toast.makeText(MainAppScreen.this, "" + phone, Toast.LENGTH_LONG).show();
                            Toast.makeText(MainAppScreen.this, "" + message, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        logDebug("Error   " + error);

                    }
                });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int pos = mHashMap.get(marker);
        JSONObject tutor = hashMapTutors.get(pos);

        CustomDialogClass dialogClass = new CustomDialogClass(this, tutor,mLastLocation.getLongitude(),mLastLocation.getLatitude(),autoCompleteTextView.getText().toString());
        dialogClass.show();
        Log.i("Position of arraylist", pos + "");
        return false;
    }
    //_________________________________________________//
    public void AutoCourseFillData() {



        AndroidNetworking.get(URLStudents.URL_AutoFillCourses)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boolean error = false;
                        String message = "";

                        try {
                            JSONArray jsonArray = response.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject item = jsonArray.getJSONObject(i);
                                autofillcoursesDB.add(item.getString("CourseName"));
                                AutoCompleteAdapter adapter = new AutoCompleteAdapter(MainAppScreen.this, android.R.layout.simple_dropdown_item_1line, android.R.id.text1, autofillcoursesDB);
                                autoCompleteTextView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error4
                        Toast.makeText(MainAppScreen.this, "" + error, Toast.LENGTH_LONG).show();

                    }
                });



    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            RequestFragment rf = new RequestFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();




            ft.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
            ft.replace(R.id.mainappcontainer, rf);


            ft.commit();

        }
        @Override
        protected Void doInBackground(Void... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            reqsend();
            //this method will be running on UI thread


        }

    }

}
