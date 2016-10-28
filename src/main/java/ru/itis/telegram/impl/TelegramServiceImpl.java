package ru.itis.telegram.impl;

import com.pengrad.telegrambot.GetUpdatesListener;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.telegram.ITelegramService;
import ru.itis.telegram.models.MessageData;
import ru.itis.telegram.models.MessageType;

import javax.annotation.PostConstruct;

/**
 * Created by Aydar Farrakhov on 14.10.16.
 */
@Service
public class TelegramServiceImpl implements ITelegramService{

    @Value("${bot.token}")
    private String TOKEN;

    private TelegramBot bot;


    @PostConstruct
    private void updateMessages() {
        bot = TelegramBotAdapter.build(TOKEN);
        bot.setGetUpdatetsListener(updates -> GetUpdatesListener.CONFIRMED_UPDATES_ALL);
    }

    @Override
    public void sendMessage(MessageType type, MessageData data) {

    }
}
