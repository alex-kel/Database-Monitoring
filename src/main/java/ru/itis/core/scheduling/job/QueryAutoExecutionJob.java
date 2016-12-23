package ru.itis.core.scheduling.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.core.entities.Condition;
import ru.itis.core.entities.Query;
import ru.itis.core.entities.User;
import ru.itis.core.service.IConditionService;
import ru.itis.core.service.IQueryService;
import ru.itis.core.service.IUserService;
import ru.itis.core.service.impl.ConfiguredDatabasesService;
import ru.itis.telegram.ITelegramService;
import ru.itis.telegram.models.MessageData;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Alex on 21.12.16.
 */
public class QueryAutoExecutionJob implements Job {

    @Autowired
    private ConfiguredDatabasesService configuredDatabasesService;

    @Autowired
    private IQueryService queryService;

    @Autowired
    private IConditionService conditionService;

    @Autowired
    private ITelegramService telegramService;

    @Autowired
    private IUserService userService;

    private long queryId;
    private long databaseId;

    public QueryAutoExecutionJob() {}

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        Query query = queryService.getQuery(queryId, databaseId);
        String result = ((String) jdbcTemplate.queryForObject(query.getStatement(), String.class)).trim();
        Condition condition = conditionService.getConditionForQuery(databaseId, queryId);
        String conditionValue = condition.getConditionValue().trim();
        if (condition != null) {
            String sign = conditionService.getConditionSign(databaseId, condition).trim();
            switch (sign) {
                case "<":
                    if (Double.valueOf(result) < Double.valueOf(conditionValue)) {
                        if (condition.getIsAlertAlreadySent() != 1) {
                            sendAlertMessage(condition.getAlertText());
                            conditionService.setAlertAlreadySent(databaseId, condition);
                        }
                    } else {
                        if (condition.getIsAlertAlreadySent() == 1) {
                            conditionService.setAlertFixed(databaseId, condition);
                            sendFixedMessageForAlert(condition.getAlertText());
                        }
                    }
                    break;
                case "<=":
                    if (Double.valueOf(result) <= Double.valueOf(conditionValue)) {
                        if (condition.getIsAlertAlreadySent() != 1) {
                            sendAlertMessage(condition.getAlertText());
                            conditionService.setAlertAlreadySent(databaseId, condition);
                        }
                    } else {
                        if (condition.getIsAlertAlreadySent() == 1) {
                            conditionService.setAlertFixed(databaseId, condition);
                            sendFixedMessageForAlert(condition.getAlertText());
                        }
                    }
                    break;
                case "=":
                    if (conditionValue.equals(result)) {
                        if (condition.getIsAlertAlreadySent() != 1) {
                            sendAlertMessage(condition.getAlertText());
                            conditionService.setAlertAlreadySent(databaseId, condition);
                        }
                    } else {
                        if (condition.getIsAlertAlreadySent() == 1) {
                            conditionService.setAlertFixed(databaseId, condition);
                            sendFixedMessageForAlert(condition.getAlertText());
                        }
                    }
                    break;
                case ">=":
                    if (Double.valueOf(result) >= Double.valueOf(conditionValue)) {
                        if (condition.getIsAlertAlreadySent() != 1) {
                            sendAlertMessage(condition.getAlertText());
                            conditionService.setAlertAlreadySent(databaseId, condition);
                        }
                    } else {
                        if (condition.getIsAlertAlreadySent() == 1) {
                            conditionService.setAlertFixed(databaseId, condition);
                            sendFixedMessageForAlert(condition.getAlertText());
                        }
                    }
                    break;
                case ">":
                    if (Double.valueOf(result) > Double.valueOf(conditionValue)) {
                        if (condition.getIsAlertAlreadySent() != 1) {
                            sendAlertMessage(condition.getAlertText());
                            conditionService.setAlertAlreadySent(databaseId, condition);
                        }
                    } else {
                        if (condition.getIsAlertAlreadySent() == 1) {
                            conditionService.setAlertFixed(databaseId, condition);
                            sendFixedMessageForAlert(condition.getAlertText());
                        }
                    }
                    break;
                case "!=":
                    if (!conditionValue.equals(result)) {
                        if (condition.getIsAlertAlreadySent() != 1) {
                            sendAlertMessage(condition.getAlertText());
                            conditionService.setAlertAlreadySent(databaseId, condition);
                        }
                    } else {
                        if (condition.getIsAlertAlreadySent() == 1) {
                            conditionService.setAlertFixed(databaseId, condition);
                            sendFixedMessageForAlert(condition.getAlertText());
                        }
                    }
                    break;
                case "like":
                    boolean matches = Pattern.matches(conditionValue, result);
                    if (!matches && condition.getIsAlertAlreadySent() != 1) {
                        sendAlertMessage(condition.getAlertText());
                        conditionService.setAlertAlreadySent(databaseId, condition);
                        break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void sendAlertMessage(String alertText) {
        List<User> users = userService.getAllChats(databaseId);
        for (User user : users) {
            telegramService.sendMessage(new MessageData(user.getId(), alertText));
        }
    }

    private void sendFixedMessageForAlert(String alertText) {
        List<User> users = userService.getAllChats(databaseId);
        for (User user : users) {
            telegramService.sendMessage(new MessageData(user.getId(), "Следующая проблема была успешно устранена: \n" + alertText));
        }
    }

    public long getQueryId() {
        return queryId;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(long databaseId) {
        this.databaseId = databaseId;
    }
}
