package com.example.utente.microapp20.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.example.utente.microapp20.Constants;
import com.example.utente.microapp20.DataType;
import com.example.utente.microapp20.GenericData;
import com.example.utente.microapp20.ImageData;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.R;
import com.example.utente.microapp20.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.Iterator;

public class MapsActivity extends MAActivity implements OnMapReadyCallback {

	private GoogleMap mMap;
	private Location loc;
	private Bitmap bm;
	boolean isMapActive = false;
	private View vMap;

	@Override
	protected void initialize(Bundle savedInstanceState) {

	}

	@Override
	protected void prepare() {

	}

	@Override
	protected void prepareView(View v) {
		vMap = (View) this.findViewById(R.id.map);
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

	public void initInputs() {
		Iterator<GenericData<?>> i = application.getData(mycomponent.getId(), DataType.LOCATION).iterator();
		if (i.hasNext())
			loc = (Location) i.next().getSingleData();
	}

	private Bitmap getBitmap() {

		// TODO: map fragment bitmap
		int w = vMap.getWidth();
		int h = vMap.getHeight();

		String url = Utils.getStaticMap(mMap, loc, w, h);
		Bitmap bitmap = Utils.getBitmapFromURL(url);

		if (bitmap == null) {
			View v1 = (View) this.findViewById(R.id.map);
			v1.setDrawingCacheEnabled(true);
			bitmap = Bitmap.createBitmap(v1.getDrawingCache());
			v1.setDrawingCacheEnabled(false);
		}
		return bitmap;
	}

	@Override
	public void beforeNext() {
		bm = getBitmap();
		ImageData img = new ImageData(mycomponent.getId(), bm);
		application.putData(mycomponent, img);
	}
	
	@Override
	protected void resume(){
	}
}
