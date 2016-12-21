package ru.itis.telegram;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itis.telegram.models.LastMessage;
import ru.itis.telegram.models.MessageType;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Aydar Farrakhov on 10.12.16.
 */
@Component
public class MessagesHolder {

    private static final long TIMEOUT = 10 * 60 * 1000;

    private ConcurrentHashMap<Long, LastMessage> messages = new ConcurrentHashMap<>();

    public LastMessage getLastMessage(Long chatId) {
        return Optional.ofNullable(messages.get(chatId))
                .orElse(null);
    }

    public void putMessage(Long chatId, MessageType type) {
        messages.put(chatId, new LastMessage(type, chatId));
    }

    public void putMessage(Long chatId, MessageType type, Long database) {
        messages.put(chatId, new LastMessage(type, chatId, database));
    }

    public void removeMessage(Long chatId) {
        messages.remove(chatId);
    }

    @Scheduled(fixedRate = 1 * 60 * 1000)
    public void reportCurrentTime() {
        messages.values().stream()
                .filter(m -> m.getCreated().getTime() + TIMEOUT <= new Date().getTime())
                .forEach(m -> messages.remove(m.getChatId()));
    }

}
