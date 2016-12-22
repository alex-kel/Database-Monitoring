package ru.itis.core.constants;

/**
 * Created by kelale on 11/28/2016.
 */
public interface CommonConstants {

  public static final String SHEMA_NAME = "monitoring_schema";

  public static final String IS_TELEGRAM_USER_EXISTS_QUERY = "SELECT * FROM monitoring_schema.user u WHERE u.id=? and u.nick=?;";
  public static final String INSERT_NEW_TELEGRAM_USER_QUERY = "INSERT INTO monitoring_schema.user (id, nick, first_name, last_name) values (?, ?, ?, ?);";
}
