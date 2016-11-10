package ru.itis.telegram.models;

/**
 * Created by Aydar Farrakhov on 28.10.16.
 */
public class MessageData {

    private Long chatId;

    private String messageBody;

    public MessageData(Long chatId, String messageBody) {
        this.chatId = chatId;
        this.messageBody = messageBody;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
