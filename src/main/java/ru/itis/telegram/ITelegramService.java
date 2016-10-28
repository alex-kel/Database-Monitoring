package ru.itis.telegram;

import ru.itis.telegram.models.MessageData;
import ru.itis.telegram.models.MessageType;

/**
 * Created by Aydar Farrakhov on 14.10.16.
 */
public interface ITelegramService {

    void sendMessage(MessageType type, MessageData data);

}
