package ru.itis.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.itis.core.constants.CommonConstants;
import ru.itis.core.entities.Condition;
import ru.itis.core.service.IConditionService;

/**
 * Created by Alex on 23.12.16.
 */
@Service
public class ConditionService implements IConditionService {

    @Autowired
    private ConfiguredDatabasesService configuredDatabasesService;

    @Override
    public Condition getConditionForQuery(long databaseId, long queryId) {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        return (Condition) jdbcTemplate.queryForObject(CommonConstants.GET_CONDITION_FOR_QUERY_ID, Condition.class, queryId);
    }

    @Override
    public String getConditionSign(long databaseId, Condition condition) {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        return (String) jdbcTemplate.queryForObject(CommonConstants.GET_CONDITION_SIGN_BY_ID, String.class, condition.getConditionSignId());
    }

    @Override
    public void setAlertFixed(long databaseId, Condition condition) {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        jdbcTemplate.update(CommonConstants.SET_ALERT_FIXED_FOR_CONDITION_ID, condition.getId());
    }

    @Override
    public void setAlertAlreadySent(long databaseId, Condition condition) {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        jdbcTemplate.update(CommonConstants.SET_ALERT_SENT_FOR_CONDITION_ID, condition.getId());
    }
}
