package ru.itis.telegram.models;

/**
 * Created by Aydar Farrakhov on 28.10.16.
 */
public enum MessageType {

    START("start", false),
    RUN_CUSTOM_QUERY("run_custom", false),
    RUN_CUSTOM_QUERY_SEL("Run Custom Query",true),
    RUN_STORED_QUERY("run_stored", false),
    RUN_STORED_QUERY_SEL("Run Stored Query", true),
    CREATE_NEW_QUERY("new", false),
    CREATE_NEW_QUERY_SEL("Create New Query", true),
    DELETE_STORED_QUERY("delete", false),
    DELETE_STORED_QUERY_SEL("Delete stored Query", true),
    LIST_STORED_QUERY_SEL("List stored Query", true);

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
}
