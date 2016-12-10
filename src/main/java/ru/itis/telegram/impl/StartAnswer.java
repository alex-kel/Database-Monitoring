package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.stereotype.Component;
import ru.itis.telegram.exception.DoTaskException;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class StartAnswer extends BaseAnswer {


    @Override
    String getText(String text, Chat chat) throws DoTaskException {
        return "Что вы хотите сделать?";
    }

    @Override
    boolean withStartKeyboard() {
        return true;
    }


}
