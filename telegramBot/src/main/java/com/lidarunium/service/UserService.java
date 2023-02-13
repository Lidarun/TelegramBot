package com.lidarunium.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserService {
    void save(Update update);
}
