package ru.itis.core.entities;

/**
 * Created by Alex on 22.12.16.
 */
public class Schedule {

    private long id, query_id, intervalInSeconds;

    public Schedule() {
    }

    public Schedule(long id, long query_id, long intervalInSeconds) {
        this.id = id;
        this.query_id = query_id;
        this.intervalInSeconds = intervalInSeconds;
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

    public long getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(long intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }
}
