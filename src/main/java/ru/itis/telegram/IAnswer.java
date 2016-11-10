package ru.itis.telegram;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
public interface IAnswer {

    SendMessage process(Chat chat, String text);

}
