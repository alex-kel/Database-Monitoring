package ru.itis.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.telegram.impl.StartAnswer;
import ru.itis.telegram.models.MessageType;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class AnswersFactory {

    @Autowired
    private StartAnswer startAnswer;

    public IAnswer getByType(MessageType type) {
        if (type == null) {
            return startAnswer;
        }
        switch (type) {
            case RUN_CUSTOM_QUERY:
                break;
            case RUN_CUSTOM_QUERY_SEL:
                break;
            case RUN_STORED_QUERY:
                break;
            case RUN_STORED_QUERY_SEL:
                break;
            case CREATE_NEW_QUERY:
                break;
            case CREATE_NEW_QUERY_SEL:
                break;
            case DELETE_STORED_QUERY:
                break;
            case DELETE_STORED_QUERY_SEL:
                break;
            case LIST_STORED_QUERY:
                break;
            case LIST_STORED_QUERY_SEL:
                break;
            default:
                return startAnswer;
        }
    }
}
