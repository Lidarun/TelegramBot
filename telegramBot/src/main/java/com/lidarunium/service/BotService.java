package com.lidarunium.service;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface BotService {
    void sendMessage(long chatId, Message message);
}
