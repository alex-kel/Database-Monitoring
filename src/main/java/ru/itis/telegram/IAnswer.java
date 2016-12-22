package ru.itis.telegram;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.request.SendMessage;
import ru.itis.telegram.exception.DoTaskException;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
public interface IAnswer {

    SendMessage process(Chat chat, String text, Long database, String data) throws DoTaskException;

}
