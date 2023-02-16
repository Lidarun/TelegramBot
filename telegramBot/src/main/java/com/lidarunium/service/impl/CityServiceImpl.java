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
import java.util.Optional;

@Log4j
@Service
public class CityServiceImpl implements CityService {
    private final CityRepository repository;
    private String urlAddress = "http://api.openweathermap.org/geo/1.0/direct?q={city}" +
            "&limit=5&appid=097e7a36c584cdb3b0001619654e57d1";

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }


    @Override//Поиск города
    public String getCityInfo(String cityName) {
        City city = getCityFromDB(cityName);
        if(city != null) return city.toString();

        urlAddress = urlAddress.replace("{city}", cityName);

        try {
            JsonNode cityInfo = new ObjectMapper().readTree(new URL(urlAddress));
            String json = cityInfo.toString();
            json = json.substring(1, json.length() - 1);
            city = getCityFromJson(json);
            System.out.println(city.toString());
            return city.toString();

        } catch(Exception e) {
            log.debug(e.getMessage() + "City: " + cityName);
        }

        return "Город "+ cityName +" не был найден!";
    }

    @Override//Извлекаем данные города из JSON
    public City getCityFromJson(String jsonCity) {
        JSONObject object = new JSONObject(jsonCity);
        City city = new City();

        city.setName(JsonPath.read(jsonCity, "$.local_names.ru"));
        city.setCountry(object.getString("country"));
        city.setLat(object.getDouble("lat"));
        city.setLon(object.getDouble("lon"));

        repository.save(city);
        return city;
    }

    @Override//Поиск города по названию из базы
    public City getCityFromDB(String city) {
        return Optional.ofNullable(repository.getCityByName(city)).orElse(null);
    }
}
