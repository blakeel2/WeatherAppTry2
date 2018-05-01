package com.example.bless.weatherapp;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Gets the weather data from the API.
 */
public class DownloadTask extends AsyncTask<String,Void,String>{

  @Override
  protected String doInBackground(String... urls) {
    String outcome = "";
    URL url;
    HttpURLConnection urlConnection = null;
    try {
      url = new URL(urls[0]);
      urlConnection = (HttpURLConnection) url.openConnection();
      InputStream input = urlConnection.getInputStream();
      InputStreamReader reader = new InputStreamReader(input);
      for (int data = reader.read(); data != -1; data++) {
        char current = (char) data;
        outcome += current;
        data = reader.read();
      }
      return outcome;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void onPostExecute(String outcome) {
    super.onPostExecute(outcome);
    try{
      JSONObject jsonObject = new JSONObject(outcome);
      String weatherInfo = jsonObject.getString("weather");
      JSONObject weatherData = new JSONObject(jsonObject.getString("main"));
      double temperature = Double.parseDouble(weatherData.getString("temp"));
      int temperatureInt = (int) (temperature * 1.8 - 459.67);
      String location = jsonObject.getString("name");
      HomeActivity.temperatureTV.setText(String.valueOf(temperatureInt + "F"));
      HomeActivity.locationTV.setText(jsonObject.getString("name"));
      JSONArray jsonArray = new JSONArray(weatherInfo);

      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonPart = jsonArray.getJSONObject(i);
      }
      } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
