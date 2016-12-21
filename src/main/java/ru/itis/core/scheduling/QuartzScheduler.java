package ru.itis.core.scheduling;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;
import ru.itis.core.scheduling.job.QueryAutoExecutionJob;

import javax.annotation.PostConstruct;

/**
 * Created by Alex on 21.12.16.
 */
@Component
public class QuartzScheduler {

    private Scheduler scheduler;

    public QuartzScheduler() throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.setJobFactory(new AutowiringSpringBeanJobFactory());
        scheduler.start();
    }

    public void scheduleQueryExecution(long databaseId, long queryId, int intervalInSeconds) throws SchedulerException {
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("everySecondsIntervalTrigger").
                withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(intervalInSeconds).repeatForever()).
                build();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(databaseId);
        stringBuilder.append("#" + queryId);
        JobDetail jobDetail = JobBuilder.newJob(QueryAutoExecutionJob.class).
                withIdentity(stringBuilder.toString()).
                build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.putAsString("DatabaseId", databaseId);
        jobDataMap.putAsString("QueryId", queryId);
        //TODO: add job params to jobdetails.jobDataMap
        QueryAutoExecutionJob job = new QueryAutoExecutionJob();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
