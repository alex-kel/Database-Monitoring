package ru.itis.core.entities;

/**
 * Created by Alex on 22.12.16.
 */
public class Schedule {

    private long id, queryId;
    private int intervalInSeconds;

    public Schedule() {
    }

    public Schedule(long id, long queryId, int intervalInSeconds) {
        this.id = id;
        this.queryId = queryId;
        this.intervalInSeconds = intervalInSeconds;
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

    public int getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(int intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }
}
