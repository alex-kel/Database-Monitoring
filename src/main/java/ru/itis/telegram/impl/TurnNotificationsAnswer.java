package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.core.service.IDatabaseService;
import ru.itis.telegram.exception.DoTaskException;

/**
 * Created by Aydar Farrakhov on 23.12.16.
 */
@Component
public class TurnNotificationsAnswer extends BaseAnswer {

    @Autowired
    private IDatabaseService databaseService;

    @Override
    String getText(String text, Chat chat, Long database, String data) throws DoTaskException {
        boolean notifications = databaseService.isUserAlreadyAdded(database, chat.id());
        if (notifications) {
            databaseService.removeUser(database, chat.id());
        } else {
            databaseService.addIfNotExist(database, chat.id(), chat.username(), chat.firstName(), chat.lastName());
        }
        return notifications ? "Уведомления отключены" : "Уведомления включены";
    }

    @Override
    boolean withStartKeyboard() {
        return true;
    }
}
