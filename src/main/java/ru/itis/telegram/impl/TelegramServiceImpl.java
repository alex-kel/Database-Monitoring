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
import ru.itis.telegram.AnswersFactory;
import ru.itis.telegram.ITelegramService;
import ru.itis.telegram.KeyboardUtil;
import ru.itis.telegram.MessagesHolder;
import ru.itis.telegram.exception.DoTaskException;
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
            MessageType type = getMessageType(text, chat);
            logger.info("Determine type: {}, chat {}", type.name(), chat.id());
            request = factory.getByType(type).process(chat, text);
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

    private MessageType getMessageType(String text, Chat chat) {
        MessageType type = messagesHolder.getLastMessage(chat.id());
        if (type == null) {
            MessageType newType = MessageType.typeByMessage(text);
            if (newType.getNextType() != null) {
                messagesHolder.putMessage(chat.id(), newType);
            }
            return newType;
        } else {
            messagesHolder.removeMessage(chat.id());
            return type.getNextType();
        }
    }

}
