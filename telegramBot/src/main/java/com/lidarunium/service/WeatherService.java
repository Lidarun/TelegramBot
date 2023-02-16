package com.lidarunium.service;

import com.lidarunium.model.Weather;

public interface WeatherService {
    Weather getWeatherInfo(String city);
    Weather getWeatherFromJson(String jsonWeather);
}
