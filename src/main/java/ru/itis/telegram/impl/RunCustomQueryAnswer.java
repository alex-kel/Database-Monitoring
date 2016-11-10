package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.telegram.IAnswer;
import ru.itis.telegram.IDatabaseService;
import ru.itis.telegram.KeyboardUtil;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class RunCustomQueryAnswer implements IAnswer {

    @Autowired
    IDatabaseService service;

    @Override
    public SendMessage process(Chat chat, String text) {

        return new SendMessage(chat.id(), service.runCustomQuery(text))
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .replyMarkup(KeyboardUtil.getStartedKeyboard());
    }


}
