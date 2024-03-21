package com.a502.backend.domain.weather;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
public class WeatherService{

    @Value("${weatherKey}")
    private String apiKey;
    public Weather weatherApi() throws Exception {
        URL url = null;
        String readLine = null;
        StringBuilder buffer = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        HttpURLConnection urlConnection = null;
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=37.56667&lon=126.97806&appid=" + apiKey + "&units=metric";

        url = new URL(apiUrl);
        urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setRequestProperty("Accept", "application/json;");

        buffer = new StringBuilder();
        if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            while((readLine = bufferedReader.readLine()) != null) {
                buffer.append(readLine).append("\n");
            }
        }

        if (bufferedWriter != null) bufferedWriter.close();
        if (bufferedReader != null) bufferedReader.close();

        return jsonParsing(buffer.toString());
    }

    public Weather jsonParsing(String json){
        JSONObject jsonObject = new JSONObject(json);
        JSONObject main = jsonObject.getJSONObject("main");
        JSONObject windObject = jsonObject.getJSONObject("wind");
        JSONObject cloudsObject = jsonObject.getJSONObject("clouds");

        int humidity;
        int cloud;
        double wind;
        double rain = 0;
        double snow = 0;

        humidity = main.getInt("humidity");
        wind = windObject.getDouble("speed");
        cloud = cloudsObject.getInt("all");

        if (jsonObject.has("rain")){
            JSONObject rainObject = jsonObject.getJSONObject("rain");
            rain = rainObject.getDouble("1h");
        }

        if (jsonObject.has("snow")){
            JSONObject snowObject = jsonObject.getJSONObject("snow");
            snow = snowObject.getDouble("1h");
        }

        return Weather.builder()
                .humidity(humidity)
                .cloud(cloud)
                .wind(wind)
                .rain(rain)
                .snow(snow)
                .build();
    }
}
