package com.a502.backend.domain.weather;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
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
    static String apiKey;
    public void weatherApi() throws Exception {
        URL url = null;
        String readLine = null;
        StringBuilder buffer = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        HttpURLConnection urlConnection = null;

        int connTimeout = 5000;
        int readTimeout = 3000;
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=37.56667&lon=126.97806&appid=e13e21fafd8d125d652d773982d07080"+"&units=metric";
//        String apiUrll = "https://api.openweathermap.org/data/2.5/weather?lat=37.56667&lon=126.97806&appid="+apiKey;
        log.info("apiKey={}",apiKey);
        try
        {
            url = new URL(apiUrl);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(3000);
            urlConnection.setRequestProperty("Accept", "application/json;");

            buffer = new StringBuilder();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
                while((readLine = bufferedReader.readLine()) != null) {
                    buffer.append(readLine).append("\n");
                }
            }
            else {
                buffer.append("code : ");
                buffer.append(urlConnection.getResponseCode()).append("\n");
                buffer.append("message : ");
                buffer.append(urlConnection.getResponseMessage()).append("\n");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) { bufferedWriter.close(); }
                if (bufferedReader != null) { bufferedReader.close(); }
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }


        System.out.println("결과 : " + buffer.toString());
        JSONObject jsonObject = new JSONObject(buffer.toString());

        JSONObject main = jsonObject.getJSONObject("main");
        JSONObject windObject = jsonObject.getJSONObject("wind");
        JSONObject cloudsObject = jsonObject.getJSONObject("clouds");
        int humidity = main.getInt("humidity");
        double wind = windObject.getDouble("speed");
        int cloud = cloudsObject.getInt("all");

        if (jsonObject.has("rain")){
            JSONObject rainObject = jsonObject.getJSONObject("rain");
            String rain = rainObject.getString("1h");
            log.info("rain={}",rain);
        }

        if (jsonObject.has("snow")){
            JSONObject snowObject = jsonObject.getJSONObject("snow");
            String snow = snowObject.getString("1h");
            log.info("snow={}",snow);
        }
        log.info("humidity={}",humidity);
        log.info("wind_speed={}",wind);
        log.info("cloudvolume={}",cloud);

    }

    public void run() {
        log.info("lilrumlelrul");
    }


}
