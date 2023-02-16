package com.lidarunium.controller;

import com.lidarunium.config.BotConfig;
import com.lidarunium.service.MessageService;
import com.lidarunium.service.UserService;
import com.lidarunium.service.CityService;
import com.lidarunium.service.WeatherService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final MessageService messageService;
    private final UserService userService;
    private final CityService cityService;
    private final WeatherService weatherService;

    public TelegramBot(BotConfig config, MessageService messageService,
                       UserService userService, CityService cityService,
                       WeatherService weatherService) {
        this.config = config;
        this.messageService = messageService;
        this.userService = userService;
        this.cityService = cityService;
        this.weatherService = weatherService;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String request = update.getMessage().getText();
        String userFName = update.getMessage().getChat().getFirstName();
        long chatId = update.getMessage().getChatId();

        switch (request) {
            case "/start":
                userService.save(update);
                sendMessage(messageService.send(chatId,
                        "Приветствую " + userFName + "!"));
                break;

            case "/weather":
//                String city = cityService.getCityInfo("Москва");
                String weather = weatherService.getWeatherInfo("Бишкек").toString();
                sendMessage(messageService.send(chatId,
                        weather));
//                sendMessage(messageService.send(chatId, city));
                break;

            default:
                sendMessage(messageService
                        .send(chatId, "Неизвестная команда!"));
        }
    }

    private void sendMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }
}
