package ru.itis.telegram;

import ru.itis.telegram.models.MessageData;

/**
 * Created by Aydar Farrakhov on 14.10.16.
 */
public interface ITelegramService {

    void sendMessage(MessageData data);

}
