package ru.itis.core.util;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.core.constants.CommonConstants;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kelale on 11/28/2016.
 */
public class DbUtils {

  public static boolean isAppSchemaAvailable(DataSource dataSource)
    throws SQLException {
    ResultSet rs = dataSource.getConnection().getMetaData().getSchemas();
    while (rs.next()) {
      if (CommonConstants.SHEMA_NAME.equals(rs.getString(1))) {
        return true;
      }
    }
    return false;
  }
}
