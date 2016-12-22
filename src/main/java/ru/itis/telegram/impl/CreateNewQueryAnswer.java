package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.stereotype.Component;
import ru.itis.core.entities.Query;
import ru.itis.telegram.exception.DoTaskException;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class CreateNewQueryAnswer extends BaseAnswer {

    private static final String SUCCESS_MESSAGE = "Запрос успешно добавлен";

    @Override
    String getText(String text, Chat chat, Long database, String data) throws DoTaskException {
        databaseService.storeNewQuery(new Query(data, text), database);
        return SUCCESS_MESSAGE;
    }

    @Override
    boolean withStartKeyboard() {
        return true;
    }


}
