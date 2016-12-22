package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.core.entities.Query;
import ru.itis.telegram.IAnswer;
import ru.itis.telegram.IDatabaseService;
import ru.itis.telegram.KeyboardUtil;
import ru.itis.telegram.exception.DoTaskException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aydar Farrakhov on 10.12.16.
 */
public abstract class BaseAnswer implements IAnswer{

    @Autowired
    protected IDatabaseService databaseService;

    abstract String getText(String text, Chat chat, Long database, String data) throws DoTaskException;

    abstract boolean withStartKeyboard();

    @Override
    public SendMessage process(Chat chat, String text, Long database, String data) throws DoTaskException {
        databaseService.addIfNotExist(chat.id(), chat.username(), chat.firstName(), chat.lastName());
        SendMessage message = new SendMessage(chat.id(), getText(text, chat, database, data))
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        if (withStartKeyboard()) {
            message.replyMarkup(KeyboardUtil.getStartedKeyboard());
        }
        return message;
    }

    protected String writeIDBaseText(Long database) throws DoTaskException {
        return "Введите ID запроса: \n" + storedQueryList(database);
    }

    protected String storedQueryList(Long database) throws DoTaskException {
        List<Query> queries = databaseService.getQueries(database);
        return "Сохраненные запросы:" + queries.stream()
                .map(q -> String.format("%d. %s", q.getId(), q.getName()))
                .collect(Collectors.joining("\n"));
    }
}
