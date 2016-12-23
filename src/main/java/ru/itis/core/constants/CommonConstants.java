package ru.itis.core.constants;

/**
 * Created by kelale on 11/28/2016.
 */
public interface CommonConstants {

  public static final String SHEMA_NAME = "monitoring_schema";

  public static final String IS_TELEGRAM_USER_EXISTS_QUERY = "SELECT * FROM monitoring_schema.user u WHERE u.id=?;";
  public static final String INSERT_NEW_TELEGRAM_USER_QUERY = "INSERT INTO monitoring_schema.user (id, nick, first_name, last_name) values (?, ?, ?, ?);";

  public static final String GET_ALL_QUERIES = "SELECT * FROM monitoring_schema.query;";
  public static final String GET_MAX_QUERY_ID = "SELECT MAX(id) FROM monitoring_schema.query;";
  public static final String GET_QUERY_BY_ID = "SELECT * FROM monitoring_schema.query q where q.id = ?;";
  public static final String DELETE_QUERY_BY_ID = "DELETE FROM monitoring_schema.query q where q.id = ?;";
  public static final String SAVE_NEW_QUERY = "INSERT INTO monitoring_schema.query (id, name, statement) values (?, ?, ?);";
}
