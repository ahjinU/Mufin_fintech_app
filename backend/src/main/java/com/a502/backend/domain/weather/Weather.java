package com.a502.backend.domain.weather;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Weather {
    int humidity;
    int cloud;
    double wind;
    double rain;
    double snow;

    int humidityLevel;
    int cloudLevel;
    int windLevel;
    int rainLevel;
    int snowLevel;
    @Builder
    public Weather(int humidity, int cloud, double wind, double rain, double snow){
        this.humidity = humidity;
        this.cloud = cloud;
        this.wind = wind;
        this.rain = rain;
        this.snow = snow;
        setLevel();
    }


    public void setLevel(){
        if (humidity <= 40) humidityLevel = 1;
        else if (humidity <= 60) humidityLevel = 2;
        else humidityLevel = 3;

        if (cloud <= 20) cloudLevel = 1;
        else if (cloud <= 70) cloudLevel = 2;
        else cloudLevel = 3;

        if (wind <= 2) windLevel = 1;
        else if (wind <= 3) windLevel = 2;
        else windLevel = 3;

        if (rain == 0) rainLevel = 0;
        else if (rain <= 3) rainLevel = 1;
        else if (rain <= 15) rainLevel = 2;
        else rainLevel = 3;

        if (snow <= 0) snowLevel = 0;
        else if (snow <= 10) snowLevel = 1;
        else if (snow <= 40) snowLevel = 2;
        else snowLevel = 3;
    }
}
