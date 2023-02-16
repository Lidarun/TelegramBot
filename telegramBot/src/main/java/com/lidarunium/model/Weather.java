package com.lidarunium.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Weather {
    String city;
    String country;
    String weatherMain;
    double temp;
    double windSpeed;

    @Override
    public String toString() {
        Locale locale = new Locale("ru", "RU");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        String date = dateFormat.format(new Date());

        weatherMain = weatherMain.substring(0, 1).toUpperCase() + weatherMain.substring(1);

        return "Погода:  " + date +
                "\nГород:  " + city + "("+country+")"+
                "\nСегодня:  " + weatherMain  +
                "\nТемпература:  " + String.format("%.1f",(temp - 273.15)) + "°C" +
                "\nСк.ветра:  " +  String.format("%.1f", windSpeed) + " м/с";
    }
}
