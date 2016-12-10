package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.stereotype.Component;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class CreateNewQuerySelAnswer extends BaseAnswer {

    @Override
    String getText(String text, Chat chat) {
        return "Введите новый SQL запрос.";
    }

    @Override
    boolean withStartKeyboard() {
        return false;
    }


}
