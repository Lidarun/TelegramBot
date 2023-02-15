package com.lidarunium.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.lidarunium.model.City;
import com.lidarunium.model.repository.CityRepository;
import com.lidarunium.service.CityService;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URL;

@Log4j
@Service
public class CityServiceImpl implements CityService {
    private final CityRepository repository;

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }


    @Override//nice
    public String getCityInfo(String cityName) {
        String urlAddress = "http://api.openweathermap.org/geo/1.0/direct?q="+ cityName
                +"&limit=5&appid=097e7a36c584cdb3b0001619654e57d1";
        try {
            JsonNode cityInfo = new ObjectMapper().readTree(new URL(urlAddress));
            String json = cityInfo.toString();
            json = json.substring(1, json.length() - 1);
            City city = getCityFromJson(json);
            System.out.println(city.toString());
            return city.toString();

        } catch(Exception e) {
            log.debug(e.getMessage() + "City: " + cityName);
        }

        return "Город "+ cityName +" не был найден!";
    }


    @Override//nice
    public City getCityFromJson(String jsonCity) {
        JSONObject object = new JSONObject(jsonCity);
        City city = new City();

        city.setRuName(JsonPath.read(jsonCity, "$.local_names.ru"));
        city.setEnName(object.getString("name"));
        city.setCountry(object.getString("country"));
        city.setLat(object.getDouble("lat"));
        city.setLon(object.getDouble("lon"));

        repository.save(city);
        return city;
    }

    //https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
}
