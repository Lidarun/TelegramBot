package com.lidarunium.service.impl;

import com.lidarunium.model.User;
import com.lidarunium.model.repository.UserRepository;
import com.lidarunium.service.UserService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Update update) {
        User user = new User();
        user.setChatId(update.getMessage().getChatId());
        user.setFName(update.getMessage().getChat().getFirstName());
        user.setLName(update.getMessage().getChat().getLastName());
        user.setUName(update.getMessage().getChat().getUserName());
        user.setRegisterDate(new Date(System.currentTimeMillis()));
        user.setActive(false);

        repository.save(user);
    }

    @Override
    public String getCityFromUserData(long chatId) {
        User user = Optional.ofNullable(repository.getUserByChatId(chatId)).orElse(null);
        if(user == null) return null;

        return user.getCity().toString();
    }
}
