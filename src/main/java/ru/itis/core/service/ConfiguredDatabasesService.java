package ru.itis.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.configuration.ConfigurationHolder;
import ru.itis.configuration.beans.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kelale on 11/28/2016.
 */
@Service
public class ConfiguredDatabasesService {

  @Autowired
  private ConfigurationHolder configurationHolder;

  // ID - database
  private Map<Long, Configuration.Database> databaseMap;

  // ID - datasource
  private Map<Long, DataSource> datasources;

  public Map<Long, Configuration.Database> getAvailableDatabases( ) {
    if (databaseMap == null) {
      fillDatabasesAndDataSources( );
    }
    return databaseMap;
  }

  public Configuration.Database getDatabaseById(long id) {
    if (databaseMap == null) {
      fillDatabasesAndDataSources( );
    }
    return databaseMap.get(id);
  }

  public Map<Long, DataSource> getAvailableDatasources() {
    if (datasources == null) {
      fillDatabasesAndDataSources();
    }
    return datasources;
  }

  public DataSource getDatasourceByDatabaseId(long id) {
    if (datasources == null) {
      fillDatabasesAndDataSources( );
    }
    return datasources.get(id);
  }

  private void fillDatabasesAndDataSources( ) {
    databaseMap = new HashMap<>( );
    datasources = new HashMap<>( );
    Configuration configuration = configurationHolder.getConnectionsConfiguration( );
    long id = 1;
    for (Configuration.Database database : configuration.getDatabase( )) {
      databaseMap.put(id, database);

      DataSource dataSource = new SingleConnectionDataSource(database.getConnection( ),
          database.getUser( ), database.getPassword( ), false);
      datasources.put(id++, dataSource);
    }
  }
}
