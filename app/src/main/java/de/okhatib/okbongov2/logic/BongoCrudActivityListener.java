package de.okhatib.okbongov2.logic;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.okhatib.okbongov2.R;

public class BongoCrudActivityListener extends ABaseListener implements
		View.OnClickListener,
															 MenuItem.OnMenuItemClickListener,
															 LocationListener {
	private static final int    REQUEST_LOCATION_UPDATE_TIME_INTERVAL_IN_MILLI_SECS = 1000;
	private static final float  REQUEST_LOCATION_UPDATE_DISTANCE_INTERVAL_IN_METERS = 1F;
	public static final  int    REQUEST_CODE_FINE_LOCATION                          = 1;
	private static final String TAG                                                 = BongoCrudActivityListener.class
			.getSimpleName();
	public static final int    REQUEST_CODE_IMAGE_CAPTURE                          = 2;
	
	//region 1. Decl. and Init Attribute
	private TextView txtvEditDate;
	private TextView txtvLongitude;
	private TextView txtvLatitude;
	private TextView txtvAltitude;
	private TextView txtvBongoPictureDescription;
	
	private EditText txtBongoNoteName;
	private EditText txtBongoNoteContent;
	
	private ImageView imgvBongoPicture;
	
	/**
	 * An/Abschlaten der Standortbestimmung
	 */
	private LocationManager locationManager;
	//endregion
	/**
	 * Standardkonstruktor zum direkten setzen
	 * der Arbeitsreferenz auf die Aktuelle Activity diese koennen sein:
	 *
	 * @param currentActivity :{@link AppCompatActivity}
	 */
	public BongoCrudActivityListener(AppCompatActivity currentActivity) {
		super(currentActivity);
		generateWidgetReferences();
	}
	
	
	@Override
	public void onLocationChanged(Location gpsLocation) {
		String strLonText = this.currentActivity.getString(R.string.strLongitudeText);
		String strLatText = this.currentActivity.getString(R.string.strLatitudeText);
		String strAltText = this.currentActivity.getString(R.string.strAltitudeText);
		
		txtvLongitude.setText(strLonText + " " + String.valueOf(gpsLocation.getLongitude()));
		txtvLatitude.setText(strLatText + " " + String.valueOf(gpsLocation.getLatitude()));
		txtvAltitude.setText(strAltText + " " + String.valueOf(gpsLocation.getAltitude()));
		
		Log.d(TAG, String.valueOf(gpsLocation.getAltitude()));
	}
	
	@Override
	public void onStatusChanged(String providerTypeHereGps, int status, Bundle extras) {
	String strOnStatusChangedMsg ="Provider:\t"+ providerTypeHereGps+ "\nStatus:\t";
	switch (status){
		case LocationProvider.AVAILABLE:
			strOnStatusChangedMsg +="Verfuegbar / Signal vorhanden\n";
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			strOnStatusChangedMsg +="Temporaer kein Signal vorhanden \n";
			break;
			case LocationProvider.OUT_OF_SERVICE:
				strOnStatusChangedMsg += "Kein Signal vorhanden \n";
				break;
				
	}
	
	}
	
	@Override
	public void onProviderEnabled(String providerTypeHereGps) {
	Log.d(TAG,"Standortbestimmung hier GPS angeschaltet");
	}
	
	@Override
	public void onProviderDisabled(String provider) {
		Log.d(TAG, "Standortbestimmung hier GPS ausgeschaltet");
	}
	//region PhotoHandling
	private void startSystemCameraApp(){
		Intent takePictureIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if(takePictureIntent.resolveActivity(this.currentActivity.getPackageManager())!=null){
			this.currentActivity
					.startActivityForResult(takePictureIntent,REQUEST_CODE_IMAGE_CAPTURE);
		}else{
			Toast.makeText(this.currentActivity, "Sorry, but you have no system camera app.",Toast.LENGTH_SHORT).show();
		}
	}
	
	//endregion
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()){
			case R.id.mnuItemSave:
				// TODO Speichern in DB
				break;
			case R.id.mnuItemDelete:
				//TODO loeschen in DB
				break;
			case R.id.mnuItemLocation:
				this.checkPermissionAndTriggerGps(true);
				break;
			case R.id.mnuItemShare:
				//TODO ShareDialog aufrufen.
				break;
		}
		return true;
	}
	
	public void checkPermissionAndTriggerGps(boolean turnOnGps) {
		if(ActivityCompat.checkSelfPermission(
				this.currentActivity, Manifest.permission.ACCESS_FINE_LOCATION)
				!=PackageManager.PERMISSION_GRANTED){
			String[] strPermissionsToRequest = {Manifest.permission.ACCESS_FINE_LOCATION};
			this.currentActivity.requestPermissions(strPermissionsToRequest,
					BongoCrudActivityListener.REQUEST_CODE_FINE_LOCATION);
		}else{
			if(turnOnGps){
				this.locationManager=(LocationManager)this.currentActivity.getSystemService(Context.LOCATION_SERVICE);
				this.locationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER,
						REQUEST_LOCATION_UPDATE_TIME_INTERVAL_IN_MILLI_SECS,
						REQUEST_LOCATION_UPDATE_TIME_INTERVAL_IN_MILLI_SECS,
						this	);
			}else{
				if(this.locationManager!=null){
					this.locationManager.removeUpdates(this);
				}
			}
		}
		
	}
	
	@Override
	public void generateWidgetReferences() {
	
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.imgvBongoPicture:
				this.startSystemCameraApp();
				break;
		}
	}
}
