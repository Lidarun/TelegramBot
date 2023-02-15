package com.lidarunium.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Weather {
    String city;
    String weatherMain;
    double temp;
    double humidity;
    double windSpeed;

    @Override
    public String toString() {
        return "Погода: " +
                "\nГород: " + city +
                "\nСегодня: " + weatherMain + '\'' +
                "\nТемпература: " + temp +
                "\nВл.воздуха: " + humidity +
                "\nСк.ветра: " + windSpeed;
    }
}
