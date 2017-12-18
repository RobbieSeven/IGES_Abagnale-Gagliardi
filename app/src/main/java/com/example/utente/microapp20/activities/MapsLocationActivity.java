package com.example.utente.microapp20.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.example.utente.microapp20.Constants;
import com.example.utente.microapp20.LocationData;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsLocationActivity extends MAActivity implements OnMapReadyCallback {

	private GoogleMap mMap;
	private Location loc;
	boolean isMapActive = false;
	boolean isGet = false;
	Marker marker;

	@Override
	protected void initialize(Bundle savedInstanceState) {
		
		isGet = false;

	}

	@Override
	protected void prepare() {
		loc= new Location(LocationManager.PASSIVE_PROVIDER);
		loc.setLatitude(Constants.unisaLocation.latitude);
		loc.setLongitude(Constants.unisaLocation.longitude);

	}

	@Override
	protected void prepareView(View v) {
		setUpMap();
	}	
	
	@Override
	protected void execute() {

	}

	@Override
	public void onMapReady(GoogleMap map) {

		this.mMap = map;

		setUpMap();
	}
	public void setUpMap() {

		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		mMap.setMyLocationEnabled(true);
		mMap.setTrafficEnabled(true);
		mMap.setIndoorEnabled(true);
		mMap.setBuildingsEnabled(true);
		mMap.getUiSettings().setZoomControlsEnabled(true);
	}

	private void centerAndZoom(LatLng center) {
		CameraUpdate cameracenter = CameraUpdateFactory.newLatLng(center);
		mMap.moveCamera(cameracenter);
		mMap.animateCamera(CameraUpdateFactory.zoomTo(9), 2000, null);
	}

	@Override
	protected boolean enableNext() {
		return isGet;
	}
	
	@Override
	protected String getNextLabel() {
		return getString(R.string.get);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void myClickHandler(View target) {
		int id = target.getId();
		if (id == R.id.sat) {
			mMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE);
		} else if (id == R.id.traffic) {
			mMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID);
		} else if (id == R.id.normal) {
			mMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL);
		}
	}

	@Override
	public void initInputs() {
	}


	@Override
	public void beforeNext() {
		LocationData ld = new LocationData(mycomponent.getId(), loc);
		application.putData(mycomponent, ld);		
	}

	@Override
	protected void resume(){
	}

}
