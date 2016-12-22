package ru.itis.telegram.impl;

import com.pengrad.telegrambot.GetUpdatesListener;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.telegram.*;
import ru.itis.telegram.exception.DoTaskException;
import ru.itis.telegram.models.LastMessage;
import ru.itis.telegram.models.MessageData;
import ru.itis.telegram.models.MessageType;

import javax.annotation.PostConstruct;

/**
 * Created by Aydar Farrakhov on 14.10.16.
 */
@Service
public class TelegramServiceImpl implements ITelegramService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String DEFAULT_ERR_MESSAGE = "При обработке произошла ошибка";

    @Value("${bot.token}")
    private String TOKEN;

    private TelegramBot bot;

    @Autowired
    private AnswersFactory factory;

    @Autowired
    private MessagesHolder messagesHolder;

    @Autowired
    private IDatabaseService databaseService;

    @PostConstruct
    private void updateMessages() {
        bot = TelegramBotAdapter.build(TOKEN);
        bot.setGetUpdatetsListener(updates -> {
            updates.stream()
                    .map(Update::message)
                    .forEach(this::processMessage);
            return GetUpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void processMessage(Message newMessage) {
        Chat chat = newMessage.chat();
        String text = newMessage.text();
        logger.info("Receive message: {} , chat {}", text, chat.id());
        SendMessage request;
        try {
            LastMessage lastMessage = getLastMessage(chat);
            MessageType type;
            Long database = null;
            String data = null;
            if (lastMessage == null) {
                type = MessageType.typeByMessage(text);
                if (type.getNextType() != null) {
                    messagesHolder.putMessage(chat.id(), type);
                }
            } else {
                type = lastMessage.getType().getNextType();
                if (lastMessage.getType().isDatabase()) {
                    database = Long.valueOf(text);
                } else if (lastMessage.getType().isHasData()) {
                    data = text;
                }
                if (database == null) {
                    database = lastMessage.getDatabase();
                }
                if (data == null) {
                    data = lastMessage.getData();
                }
                if (type.getNextType() != null) {
                    messagesHolder.putMessage(chat.id(), type, database, data);
                }
            }
            logger.info("Determine type: {}, chat {}", type != null ? type.name() : null, chat.id());
            request = factory.getByType(type).process(chat, text, database, data);
            databaseService.addIfNotExist(chat.id(), chat.username(), chat.firstName(), chat.lastName());
        } catch (DoTaskException e) {
            request = getErrorAnswer(chat, e.getMessage());
            logger.error("Error {}, when processing message: {}, chat: {}", e.getMessage(),
                    text, chat.id());
        } catch (Exception e) {
            logger.error("Error {}, when processing message: {}, chat: {}", e.getMessage(),
                    text, chat.id());
            e.printStackTrace();
            request = getErrorAnswer(chat, null);
        }
        logger.info("Send response to {}", chat.id());
        SendResponse sendResponse = bot.execute(request);
        sendResponse.isOk();
        logger.info("Answered to {}", chat.id());
    }

    @Override
    public boolean sendMessage(MessageData data) {
        SendMessage request = new SendMessage(data.getChatId(), data.getMessageBody())
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .replyMarkup(KeyboardUtil.getStartedKeyboard());
        SendResponse sendResponse = bot.execute(request);
        return sendResponse.isOk();
    }

    private SendMessage getErrorAnswer(Chat chat, String message) {
        return new SendMessage(chat.id(), message == null ? DEFAULT_ERR_MESSAGE : message)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .replyMarkup(KeyboardUtil.getStartedKeyboard());
    }

    private LastMessage getLastMessage(Chat chat) {
        LastMessage message = messagesHolder.getLastMessage(chat.id());
        if (message == null) {
           return null;
        } else {
            messagesHolder.removeMessage(chat.id());
            return message;
        }
    }

}
