package ru.itis.telegram.impl;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.itis.telegram.IAnswer;
import ru.itis.telegram.models.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class StartAnswer implements IAnswer {


    @Override
    public SendMessage process(Chat chat, String text) {
        List<KeyboardButton> buttons = getStartButtons();
        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{buttons.get(0), buttons.get(1)},
                new KeyboardButton[]{buttons.get(2), buttons.get(3)},
                new KeyboardButton[]{buttons.get(4)}
        )
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);

        return new SendMessage(chat.id(), "What do you want to do?")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .replyMarkup(replyKeyboardMarkup);
    }

    private List<KeyboardButton> getStartButtons() {
        List<KeyboardButton> buttons = new ArrayList<>();
        for (MessageType messageType : MessageType.values()) {
            if (messageType.isShowWhenStart()) {
                buttons.add(new KeyboardButton(messageType.getKeyWord()));
            }
        }
        return buttons;
    }


}
