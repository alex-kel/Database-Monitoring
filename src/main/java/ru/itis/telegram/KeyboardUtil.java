package ru.itis.telegram;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import ru.itis.telegram.models.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
public class KeyboardUtil {

    public static Keyboard getStartedKeyboard() {
        List<KeyboardButton> buttons = getStartButtons();
        return new ReplyKeyboardMarkup(
                new KeyboardButton[]{buttons.get(0), buttons.get(1)},
                new KeyboardButton[]{buttons.get(2), buttons.get(3)},
                new KeyboardButton[]{buttons.get(4)}
        )
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);
    }


    private static List<KeyboardButton> getStartButtons() {
        List<KeyboardButton> buttons = new ArrayList<>();
        for (MessageType messageType : MessageType.values()) {
            if (messageType.isShowWhenStart()) {
                buttons.add(new KeyboardButton(messageType.getKeyWord()));
            }
        }
        return buttons;
    }

}
