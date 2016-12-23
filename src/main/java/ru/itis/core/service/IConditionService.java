package ru.itis.core.service;

import ru.itis.core.entities.Condition;

/**
 * Created by Alex on 23.12.16.
 */
public interface IConditionService {

    Condition getConditionForQuery(long databaseId, long queryId);

    String getConditionSign(long databaseId, Condition condition);

    void setAlertFixed(long databaseId, Condition condition);

    void setAlertAlreadySent(long databaseId, Condition condition);
}
