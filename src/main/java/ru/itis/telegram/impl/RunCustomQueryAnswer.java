package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.stereotype.Component;
import ru.itis.telegram.exception.DoTaskException;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class RunCustomQueryAnswer extends BaseAnswer {

    @Override
    String getText(String text, Chat chat) throws DoTaskException {
        return "Результат: " + databaseService.runCustomQuery(text);
    }

    @Override
    boolean withStartKeyboard() {
        return true;
    }

}
