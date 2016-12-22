package ru.itis.core.scheduling.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.core.service.impl.ConfiguredDatabasesService;

import javax.sql.DataSource;

/**
 * Created by Alex on 21.12.16.
 */
public class QueryAutoExecutionJob implements Job {

    @Autowired
    private ConfiguredDatabasesService configuredDatabasesService;

    private long queryId;
    private long databeaseId;

    public QueryAutoExecutionJob() {}

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DataSource dataSource = configuredDatabasesService.getDatasourceByDatabaseId(databeaseId);
    }

    public long getQueryId() {
        return queryId;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public long getDatabeaseId() {
        return databeaseId;
    }

    public void setDatabeaseId(long databeaseId) {
        this.databeaseId = databeaseId;
    }
}
