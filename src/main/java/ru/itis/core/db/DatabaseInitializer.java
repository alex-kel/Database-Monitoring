package ru.itis.core.db;

import org.apache.commons.io.IOUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.itis.core.constants.CommonConstants;
import ru.itis.core.entities.Schedule;
import ru.itis.core.scheduling.QuartzScheduler;
import ru.itis.core.service.impl.ConfiguredDatabasesService;
import ru.itis.core.util.DbUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by kelale on 11/28/2016.
 */
@Component
public class DatabaseInitializer {

  @Autowired
  private ConfiguredDatabasesService configuredDatabasesService;

  @Autowired
  private QuartzScheduler quartzScheduler;

  public void initialize() {
    for (Map.Entry<Long, DataSource> entry : configuredDatabasesService.getAvailableDatasources().entrySet()) {
      try {
        initDatabase(entry.getValue());
        initScheduledJobs(entry.getKey());
      } catch (SQLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (SchedulerException e) {
        e.printStackTrace();
      }
    }
  }

  private void initScheduledJobs(long databaseId) throws SchedulerException {
    JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
    List<Schedule> schedules = jdbcTemplate.query(CommonConstants.GET_ALL_SCHEDULES, new BeanPropertyRowMapper(Schedule.class));
    for (Schedule schedule : schedules) {
      quartzScheduler.scheduleQueryExecution(databaseId, schedule.getQueryId(), schedule.getIntervalInSeconds());
    }
  }

  private void initDatabase(DataSource dataSource) throws SQLException, IOException {
    if (!isSchemaExists(dataSource)) {
      initSchema(dataSource);
      loadStatements(dataSource);
    }
  }

  private boolean isSchemaExists(DataSource dataSource) throws SQLException {
    return DbUtils.isAppSchemaAvailable(dataSource);
  }

  private void initSchema(DataSource dataSource) throws IOException {
    String schemaScript = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("statements/initial_script.sql"));
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.update(schemaScript);
  }

  private void loadStatements(DataSource dataSource) throws IOException {
    String statementsScript = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("statements/statements.sql"));
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.update(statementsScript);
  }
}
