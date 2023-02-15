package com.lidarunium.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "cities_info")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String ruName;
    String enName;
    String country;
    double lat;
    double lon;

    @Override
    public String toString() {
        return "Город: "+ ruName +
                "\nСтрана: " + country + '\'' +
                "\nДолгота: " + lat +
                "\nШирота: " + lon;
    }
}
