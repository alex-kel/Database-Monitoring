package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.stereotype.Component;
import ru.itis.telegram.exception.DoTaskException;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class DeleteQueryAnswer extends BaseAnswer {

    private static final String SUCCESS_MESSAGE = "Запрос успешно удален";
    private static final String NOT_FOUND = "SQL Запрос не найден";

    @Override
    String getText(String text, Chat chat) throws DoTaskException{
        try {
            databaseService.deleteStoredQuery(Long.valueOf(text));
            return SUCCESS_MESSAGE;
        } catch (NumberFormatException e) {
            return NOT_FOUND;
        }
    }

    @Override
    boolean withStartKeyboard() {
        return true;
    }

}
