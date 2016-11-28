package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.itis.telegram.IAnswer;
import ru.itis.telegram.KeyboardUtil;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class StartAnswer implements IAnswer {


    @Override
    public SendMessage process(Chat chat, String text) {


        return new SendMessage(chat.id(), "What do you want to do?")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .replyMarkup(KeyboardUtil.getStartedKeyboard());
    }



}