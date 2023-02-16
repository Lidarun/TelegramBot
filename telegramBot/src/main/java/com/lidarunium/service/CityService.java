package com.lidarunium.service;

import com.lidarunium.model.City;

public interface CityService {
    String getCityInfo(String city);
    City getCityFromJson(String string);
    City getCityFromDB(String city);
}
