package com.example.osmstreetviwr;


import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.PathOverlay;
import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity implements LocationListener {
    private LocationManager myLocationManager;
    private Object mapView;
    static PathOverlay myPath ;
    private MapView map;
	private MapController mapController;
	Handler mHandler = new Handler();
@Override
public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       map = (MapView) findViewById(R.id.openmapview);
       map.setTileSource(TileSourceFactory.MAPQUESTOSM);

     //  myPath= new PathOverlay(Color.RED, this);
      // map.setBuiltInZoomControls(true);
      // map.setMultiTouchControls(true);
      
       
       IMapController mapController = map.getController();
       mapController.setZoom(15);
       GeoPoint startPoint = new GeoPoint(31.5622,74.3232);
       mapController.setCenter(startPoint);
       myLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
       myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, this);
      // GeoPoint startPoint = new GeoPoint(48.8583, 2,2944);
     
   

//
       new Thread(new Runnable() {
			@Override
			public void run() {

				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(7000);
						mHandler.post(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								// Write your code here to update the UI.
								// Loading products in Background Thread
								Toast.makeText(MainActivity.this, "refresh", Toast.LENGTH_LONG).show();
								//onLocationChanged(null );
							}
						});
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}).start();


}



@Override
public void onLocationChanged(Location location) {
	// TODO Auto-generated method stub
	int latitude = (int) (location.getLatitude() * 1E6);
    int longitude = (int) (location.getLongitude() * 1E6);
    GeoPoint geopoint = new GeoPoint(latitude, longitude);
    mapController.setCenter(geopoint);
  //  myPath.addPoint(geopoint);
   // map.getOverlays().add(myPath);
    map.invalidate();
}

@Override
public void onProviderDisabled(String arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onProviderEnabled(String arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	// TODO Auto-generated method stub
	
}
}
