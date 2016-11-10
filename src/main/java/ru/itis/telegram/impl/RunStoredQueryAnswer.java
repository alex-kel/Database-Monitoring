package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.telegram.IAnswer;
import ru.itis.telegram.IDatabaseService;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class RunStoredQueryAnswer implements IAnswer {

    @Autowired
    IDatabaseService databaseService;


    @Override
    public SendMessage process(Chat chat, String text) {
        databaseService.runStoredQuery(Long.valueOf(text));
        return new SendMessage(chat.id(), "some result queiries")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
    }


}
