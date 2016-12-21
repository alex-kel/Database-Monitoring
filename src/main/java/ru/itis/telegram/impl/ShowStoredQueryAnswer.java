package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.stereotype.Component;
import ru.itis.telegram.exception.DoTaskException;

import java.util.Optional;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class ShowStoredQueryAnswer extends BaseAnswer {

    private static final String NOT_FOUND = "SQL Запрос не найден";

    @Override
    String getText(String text, Chat chat, Long database) throws DoTaskException {
        try {
            return Optional.ofNullable(databaseService.getQuery(Long.valueOf(text), database))
                    .map(query -> String.format("%d. %s : %s", query.getId(),
                            query.getName(), query.getStatement()))
                    .orElse(NOT_FOUND);
        } catch (NumberFormatException e) {
            return NOT_FOUND;
        }
    }

    @Override
    boolean withStartKeyboard() {
        return true;
    }

}

