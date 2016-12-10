package ru.itis.telegram;

import ru.itis.telegram.models.MessageData;

/**
 * Created by Aydar Farrakhov on 14.10.16.
 */
public interface ITelegramService {

    /**
     * Отправляет сообщение определенному пользователю
     *
     * @param data текст и получатель сообщения
     * @return <code>true </code> если сообщение отправлено, иначе <code>false</code>
     */
    boolean sendMessage(MessageData data);

}
