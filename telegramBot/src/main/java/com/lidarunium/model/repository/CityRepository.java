package com.lidarunium.model.repository;

import com.lidarunium.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City getCityByName(String name);
}
