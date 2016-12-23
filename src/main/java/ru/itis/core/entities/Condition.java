package ru.itis.core.entities;

/**
 * Created by Alex on 22.12.16.
 */
public class Condition {

    private long id, queryId, conditionSignId;
    private String alertText, conditionValue;
    private int isAlertAlreadySent;

    public Condition() {
    }

    public Condition(long id, long conditionSignId, String conditionValue, long queryId, String alertText, int isAlertAlreadySent) {
        this.id = id;
        this.conditionSignId = conditionSignId;
        this.conditionValue = conditionValue;
        this.queryId = queryId;
        this.alertText = alertText;
        this.isAlertAlreadySent = isAlertAlreadySent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQueryId() {
        return queryId;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public String getAlertText() {
        return alertText;
    }

    public void setAlertText(String alertText) {
        this.alertText = alertText;
    }

    public int getIsAlertAlreadySent() {
        return isAlertAlreadySent;
    }

    public void setIsAlertAlreadySent(int isAlertAlreadySent) {
        this.isAlertAlreadySent = isAlertAlreadySent;
    }

    public long getConditionSignId() {
        return conditionSignId;
    }

    public void setConditionSignId(long conditionSignId) {
        this.conditionSignId = conditionSignId;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }
}
