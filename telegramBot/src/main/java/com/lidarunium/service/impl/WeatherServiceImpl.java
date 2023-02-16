package com.lidarunium.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.lidarunium.model.City;
import com.lidarunium.model.Weather;
import com.lidarunium.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URL;


@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {
    private String urlWeather = "https://api.openweathermap.org/data/2.5/weather?" +
            "q={city}&appid={API key}&lang={lang}";

    @Override
    public Weather getWeatherInfo(String city) {

        urlWeather = urlWeather.replace("{city}", city);
        urlWeather = urlWeather.replace("{API key}",
                "097e7a36c584cdb3b0001619654e57d1");
        urlWeather = urlWeather.replace("{lang}", "ru");

        try {
            JsonNode weatherInfo = new ObjectMapper().readTree(new URL(urlWeather));
            String json = weatherInfo.toString();
            return getWeatherFromJson(json);

        } catch(Exception e) {
            log.debug(e.getMessage() + "City: " + city);
        }
        return null;
    }

    @Override
    public Weather getWeatherFromJson(String jsonWeather) {
        Weather weather = new Weather();

        weather.setWeatherMain(JsonPath.read(jsonWeather,
                        "$.weather[0].description"));
        weather.setCountry(JsonPath.read(jsonWeather, "$.sys.country"));

        weather.setCity(JsonPath.read(jsonWeather, "$.name"));
        weather.setTemp(JsonPath.read(jsonWeather, "$.main.temp"));
        weather.setWindSpeed(JsonPath.read(jsonWeather, "$.wind.speed"));

        System.out.println(weather);

        return weather;
    }

}
