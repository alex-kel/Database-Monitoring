package ru.itis.telegram.models;

/**
 * Created by Aydar Farrakhov on 28.10.16.
 */
public enum MessageType {

    START("Начало", false),
    RUN_CUSTOM_QUERY("run_custom", false),
    RUN_CUSTOM_QUERY_SEL("Запустить скрипт",true),
    RUN_STORED_QUERY("run_stored", false),
    RUN_STORED_QUERY_SEL("Запустить сохранненый запрос", true),
    CREATE_NEW_QUERY("new", false),
    CREATE_NEW_QUERY_SEL("Создать новый запрос", true),
    DELETE_STORED_QUERY("delete", false),
    DELETE_STORED_QUERY_SEL("Удалить сохраненный запрос", true),
    LIST_STORED_QUERY_SEL("Список сохранненных запросов", true),
    SHOW_STORED_QUERY_SEL("Показать сохраненный запрос", true),
    SHOW_STORED_QUERY("show_stored", false);

    MessageType(String keyWord, boolean showWhenStart) {
        this.keyWord = keyWord;
        this.showWhenStart = showWhenStart;
    }

    private String keyWord;
    private boolean showWhenStart;

    public static MessageType typeByMessage(String text) {
        for (MessageType messageType : values()) {
            if (text.toLowerCase().startsWith(messageType.keyWord.toLowerCase())) {
                return messageType;
            }
        }
        return START;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public boolean isShowWhenStart() {
        return showWhenStart;
    }

    public MessageType getNextType() {
        switch (this) {
            case RUN_CUSTOM_QUERY_SEL:
                return RUN_CUSTOM_QUERY;
            case RUN_STORED_QUERY_SEL:
                return RUN_STORED_QUERY;
            case CREATE_NEW_QUERY_SEL:
                return CREATE_NEW_QUERY;
            case DELETE_STORED_QUERY_SEL:
                return DELETE_STORED_QUERY;
            case SHOW_STORED_QUERY_SEL:
                return SHOW_STORED_QUERY;
            default:
                return null;
        }
    }
}
