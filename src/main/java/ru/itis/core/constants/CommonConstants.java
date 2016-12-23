package ru.itis.core.constants;

/**
 * Created by kelale on 11/28/2016.
 */
public interface CommonConstants {

  String SHEMA_NAME = "monitoring_schema";

  String IS_TELEGRAM_USER_EXISTS_QUERY = "SELECT * FROM monitoring_schema.user u WHERE u.id=?;";
  String INSERT_NEW_TELEGRAM_USER_QUERY = "INSERT INTO monitoring_schema.user (id, nick, first_name, last_name) values (?, ?, ?, ?);";

  //Query Service
  String GET_ALL_QUERIES = "SELECT * FROM monitoring_schema.query;";
  String GET_MAX_QUERY_ID = "SELECT MAX(id) FROM monitoring_schema.query;";
  String GET_QUERY_BY_ID = "SELECT * FROM monitoring_schema.query q where q.id = ?;";
  String DELETE_QUERY_BY_ID = "DELETE FROM monitoring_schema.query q where q.id = ?;";
  String SAVE_NEW_QUERY = "INSERT INTO monitoring_schema.query (id, name, statement) values (?, ?, ?);";

  //Schedule
  String GET_ALL_SCHEDULES = "SELECT * FROM monitoring_schema.schedule;";

  //Users
  String GET_ALL_USERS = "SELECT * FROM monitoring_schema.user";

  //Condition
  String GET_CONDITION_FOR_QUERY_ID = "SELECT * FROM monitoring_schema.condition c WHERE c.query_id = ?";
  String GET_CONDITION_SIGN_BY_ID = "SELECT cs.condition_sign FROM monitoring_schema.condition_sign cs WHERE id = ?";
  String SET_ALERT_FIXED_FOR_CONDITION_ID = "UPDATE monitoring_schema.condition SET is_alert_already_sent = 0 WHERE id = ?";
  String SET_ALERT_SENT_FOR_CONDITION_ID = "UPDATE monitoring_schema.condition SET is_alert_already_sent = 1 WHERE id = ?";
}
