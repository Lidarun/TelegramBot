package com.lidarunium.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "tb_users")
public class User {
    @Id
    long chatId;
    String fName;
    String lName;
    String uName;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date registerDate;
    boolean active;

    @Override
    public String toString() {
        return  "\nИмя: " + String.format("%14s %20s",fName, (lName == null ? " ": lName)) +
                "\nНикнейм: " + String.format("%12s",uName) +
                "\nДобавлен: " + String.format("%11S",registerDate);
    }
}
