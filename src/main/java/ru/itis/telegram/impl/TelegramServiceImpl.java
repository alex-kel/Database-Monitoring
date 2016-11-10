package ru.itis.telegram.impl;

import com.pengrad.telegrambot.GetUpdatesListener;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.telegram.AnswersFactory;
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

    @Autowired
    private AnswersFactory factory;

    @PostConstruct
    private void updateMessages() {
        bot = TelegramBotAdapter.build(TOKEN);
        bot.setGetUpdatetsListener(updates -> {
            updates.stream()
                    .map(Update::message)
                    .forEach(this::processMessage);
            return GetUpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void processMessage(Message newMessage) {
        MessageType type = MessageType.typeByMessage(newMessage.text());
        String text = cleanText(newMessage.text(), type);
        SendMessage request = factory.getByType(type).process(newMessage.chat(), text);
        SendResponse sendResponse = bot.execute(request);
        sendResponse.isOk();
    }

    private String cleanText(String newMessage, MessageType type) {
        return newMessage.substring(type.getKeyWord().length()).trim();
    }

    @Override
    public void sendMessage(MessageType type, MessageData data) {

    }

}
