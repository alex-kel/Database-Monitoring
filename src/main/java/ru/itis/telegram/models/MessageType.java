package ru.itis.telegram.models;

/**
 * Created by Aydar Farrakhov on 28.10.16.
 */
public enum MessageType {

    START("Начало", false, false),
    RUN_CUSTOM_QUERY("run_custom", false, false),
    RUN_CUSTOM_QUERY_SEL("run_custom_sel", false, false),
    RUN_STORED_QUERY("run_stored", false, false),
    RUN_STORED_QUERY_SEL("run_stored_sel", false, false),
    CREATE_NEW_QUERY("new", false, false),
    CREATE_NEW_QUERY_SEL("create_new_sel", false, false),
    DELETE_STORED_QUERY("delete", false, false),
    DELETE_STORED_QUERY_SEL("delete_stored_sel", false, false),
    LIST_STORED_QUERY_SEL("show_stored_list_sel", false, false),
    SHOW_STORED_QUERY_SEL("show_stored_sel", false, false),
    SHOW_STORED_QUERY("show_stored", false, false),
    CHOOSE_DATABASE_CUSTOM_QUERY("Запустить скрипт", true, true),
    CHOOSE_DATABASE_RUN_STORED_QUERY("Запустить сохранненый запрос", true, true ),
    CHOOSE_DATABASE_NEW_QUERY("Создать новый запрос", true, true),
    CHOOSE_DATABASE_DELETE("Удалить сохраненный запрос", true, true),
    CHOOSE_DATABASE_LIST_QUERIES("Список сохранненных запросов", true, true),
    CHOOSE_DATABASE_SHOW_QUERY("Показать сохраненный запрос", true, true);

    MessageType(String keyWord, boolean showWhenStart, boolean database) {
        this.keyWord = keyWord;
        this.showWhenStart = showWhenStart;
        this.database = database;
    }

    private String keyWord;
    private boolean showWhenStart;
    private boolean database;

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
            case CHOOSE_DATABASE_CUSTOM_QUERY:
                return RUN_CUSTOM_QUERY_SEL;
            case RUN_CUSTOM_QUERY_SEL:
                return RUN_CUSTOM_QUERY;
            case CHOOSE_DATABASE_RUN_STORED_QUERY:
                return RUN_STORED_QUERY_SEL;
            case RUN_STORED_QUERY_SEL:
                return RUN_STORED_QUERY;
            case CHOOSE_DATABASE_NEW_QUERY:
                return CREATE_NEW_QUERY_SEL;
            case CREATE_NEW_QUERY_SEL:
                return CREATE_NEW_QUERY;
            case CHOOSE_DATABASE_DELETE:
                return DELETE_STORED_QUERY_SEL;
            case DELETE_STORED_QUERY_SEL:
                return DELETE_STORED_QUERY;
            case CHOOSE_DATABASE_SHOW_QUERY:
                return SHOW_STORED_QUERY_SEL;
            case SHOW_STORED_QUERY_SEL:
                return SHOW_STORED_QUERY;
            case CHOOSE_DATABASE_LIST_QUERIES:
                return LIST_STORED_QUERY_SEL;
            default:
                return null;
        }
    }

    public boolean isDatabase() {
        return database;
    }

    public void setDatabase(boolean database) {
        this.database = database;
    }
}
