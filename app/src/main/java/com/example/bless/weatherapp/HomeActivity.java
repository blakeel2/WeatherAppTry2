package com.example.bless.weatherapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

  static TextView locationTV;
  static TextView temperatureTV;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    locationTV = (TextView) findViewById(R.id.nameTextView);
    temperatureTV = (TextView) findViewById(R.id.temperatureTextView);

    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    String provider = locationManager.getBestProvider(new Criteria(), false);
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
    Location location = locationManager.getLastKnownLocation(provider);
    double latitude = location.getLatitude();
    double longitude = location.getLongitude();
            DownloadTask task = new DownloadTask();
    task.execute("http://samples.openweathermap.org/data/2.5/weather?lat=" +
            String.valueOf(latitude) + "&lon=" + String.valueOf(longitude) +"&appid=" +
            "174d19403351b6c8535769b0a1e5015f");
  }



  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  private void testUpload() {
    int i = 1 + 1;

    int ii = 2 + 2;

    int iii = i + ii;
  }
}
