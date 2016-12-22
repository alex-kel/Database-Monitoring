package ru.itis.core.db;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.itis.core.service.impl.ConfiguredDatabasesService;
import ru.itis.core.util.DbUtils;

import javax.sql.DataSource;
import java.io.IOException;
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
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void initDatabase(DataSource dataSource) throws SQLException, IOException {
    if (!isSchemaExists(dataSource)) {
      initSchema(dataSource);
      loadStatements();
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

  private void loadStatements() {

  }
}
