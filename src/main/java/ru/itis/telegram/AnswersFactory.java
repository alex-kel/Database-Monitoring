package ru.itis.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.telegram.impl.*;
import ru.itis.telegram.models.MessageType;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Component
public class AnswersFactory {

    @Autowired
    private StartAnswer startAnswer;
    @Autowired
    private RunCustomQuerySelAnswer customQuerySelAnswer;
    @Autowired
    private RunCustomQueryAnswer customQueryAnswer;
    @Autowired
    private RunStoredQueryAnswer runStoredQueryAnswer;
    @Autowired
    private RunStoredQuerySelAnswer runStoredQuerySelAnswer;
    @Autowired
    private CreateNewQueryAnswer createNewQueryAnswer;
    @Autowired
    private CreateNewQuerySelAnswer createNewQuerySelAnswer;
    @Autowired
    private DeleteQueryAnswer deleteQueryAnswer;
    @Autowired
    private DeleteQuerySelAnswer deleteQuerySelAnswer;
    @Autowired
    private ListStoredQueryAnswer listStoredQueryAnswer;

    public IAnswer getByType(MessageType type) {
        if (type == null) {
            return startAnswer;
        }
        switch (type) {
            case RUN_CUSTOM_QUERY:
                return customQueryAnswer;
            case RUN_CUSTOM_QUERY_SEL:
                return customQuerySelAnswer;
            case RUN_STORED_QUERY:
                return runStoredQueryAnswer;
            case RUN_STORED_QUERY_SEL:
                return runStoredQuerySelAnswer;
            case CREATE_NEW_QUERY:
                return createNewQueryAnswer;
            case CREATE_NEW_QUERY_SEL:
                return createNewQuerySelAnswer;
            case DELETE_STORED_QUERY:
                return deleteQueryAnswer;
            case DELETE_STORED_QUERY_SEL:
                return deleteQuerySelAnswer;
            case LIST_STORED_QUERY_SEL:
                return listStoredQueryAnswer;
            default:
                return startAnswer;
        }
    }
}
