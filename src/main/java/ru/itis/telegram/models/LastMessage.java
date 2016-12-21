package ru.itis.telegram.models;

import java.util.Date;

/**
 * Created by Aydar Farrakhov on 10.12.16.
 */
public class LastMessage {

    private MessageType type;

    private Date created;

    private long chatId;

    private Long database;

    public LastMessage(MessageType type, long chatId) {
        this.type = type;
        this.created = new Date();
        this.chatId = chatId;
    }

    public LastMessage(MessageType type, Long chatId, Long database) {
        this.type = type;
        this.created = new Date();
        this.chatId = chatId;
        this.database = database;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public Long getDatabase() {
        return database;
    }

    public void setDatabase(Long database) {
        this.database = database;
    }
}
