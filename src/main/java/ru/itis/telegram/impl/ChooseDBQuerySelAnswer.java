package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.core.service.impl.ConfiguredDatabasesService;
import ru.itis.telegram.exception.DoTaskException;

/**
 * Created by Aydar Farrakhov on 21.12.16.
 */
@Component
public class ChooseDBQuerySelAnswer extends BaseAnswer{

    @Autowired
    private ConfiguredDatabasesService databasesService;

    @Override
    protected String getText(String text, Chat chat, Long database, String data) throws DoTaskException {
        final String[] databases = {""};
        databasesService.getAvailableDatabases().entrySet()
                .forEach(s2 -> databases[0] += String.format("%d: %s\n", s2.getKey(), s2.getValue().getName()));
        return "Выберите базу данных: \n" + databases[0];
    }

    @Override
    boolean withStartKeyboard() {
        return false;
    }
}
