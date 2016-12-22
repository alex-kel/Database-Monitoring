package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.stereotype.Component;
import ru.itis.telegram.exception.DoTaskException;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class RunStoredQueryAnswer extends BaseAnswer {

    private static final String NOT_FOUND = "Запрос не найден";

    @Override
    String getText(String text, Chat chat, Long database, String data) throws DoTaskException {
        try {
            return "Результат: " + databaseService.runStoredQuery(Long.valueOf(text), database);
        } catch (NumberFormatException e) {
            return NOT_FOUND;
        }
    }

    @Override
    boolean withStartKeyboard() {
        return true;
    }

}
