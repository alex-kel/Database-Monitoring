package ru.itis.core.scheduling;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.core.scheduling.job.QueryAutoExecutionJob;

import javax.annotation.PostConstruct;

/**
 * Created by Alex on 21.12.16.
 */
@Component
public class QuartzScheduler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Scheduler scheduler;

    @Autowired
    private AutowiringSpringBeanJobFactory jobFactory;

    @PostConstruct
    public void createScheduler() throws SchedulerException {
        scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.setJobFactory(jobFactory);
        scheduler.start();
    }

    public void scheduleQueryExecution(long databaseId, long queryId, int intervalInSeconds) throws SchedulerException {
        logger.info("Scheduling job for query #" + queryId + "and db #" + databaseId + "with interval of " + intervalInSeconds + "seconds");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(databaseId);
        stringBuilder.append("#" + queryId);
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("everySecondsIntervalTrigger" + stringBuilder.toString()).
                withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(intervalInSeconds).repeatForever()).
                build();
        JobDetail jobDetail = JobBuilder.newJob(QueryAutoExecutionJob.class).
                withIdentity(stringBuilder.toString()).
                build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.putAsString("DatabaseId", databaseId);
        jobDataMap.putAsString("QueryId", queryId);
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
