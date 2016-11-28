package ru.itis.core.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.core.service.ConfiguredDatabasesService;
import ru.itis.core.util.DbUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by kelale on 11/28/2016.
 */
@Component
public class DatabaseInitializer {

  @Autowired
  private ConfiguredDatabasesService configuredDatabasesService;

  public void initialize() {
    for (Map.Entry<Long, DataSource> entry : configuredDatabasesService.getAvailableDatasources().entrySet()) {
      try {
        initDatabase(entry.getValue());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private void initDatabase(DataSource dataSource) throws SQLException {
    if (!isSchemaExists(dataSource)) {
      initSchema();
      loadStatements();
    } else {
      if (!statementsWereLoaded()) {
        loadStatements();
      }
    }
  }

  private boolean isSchemaExists(DataSource dataSource) throws SQLException {
    return DbUtils.isAppSchemaAvailable(dataSource);
  }

  private boolean statementsWereLoaded() {
    return true;
  }

  private void initSchema() {

  }

  private void loadStatements() {

  }
}
