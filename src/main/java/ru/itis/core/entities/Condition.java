package ru.itis.core.entities;

/**
 * Created by Alex on 22.12.16.
 */
public class Condition {

    private long id, query_id;
    private String conditionType, alertText;

    public Condition() {
    }

    public Condition(long id, long query_id, String conditionType, String alertText) {
        this.id = id;
        this.query_id = query_id;
        this.conditionType = conditionType;
        this.alertText = alertText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuery_id() {
        return query_id;
    }

    public void setQuery_id(long query_id) {
        this.query_id = query_id;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getAlertText() {
        return alertText;
    }

    public void setAlertText(String alertText) {
        this.alertText = alertText;
    }
}
