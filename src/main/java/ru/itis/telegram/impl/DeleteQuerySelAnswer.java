package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.stereotype.Component;
import ru.itis.telegram.exception.DoTaskException;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class DeleteQuerySelAnswer extends BaseAnswer {

    @Override
    String getText(String text, Chat chat, Long database) throws DoTaskException {
        return writeIDBaseText(database);
    }

    @Override
    boolean withStartKeyboard() {
        return false;
    }
}
