CREATE SCHEMA monitoring_schema;

CREATE TABLE monitoring_schema.dbms_type(
  id INT PRIMARY KEY ,
  dbms_type VARCHAR(20)
);

INSERT INTO monitoring_schema.dbms_type(id, dbms_type) VALUES (1, 'postgresql'), (2, 'oracle');

CREATE sequence  monitoring_schema.query_id_seq;
CREATE TABLE monitoring_schema.query (
  id        INT DEFAULT NEXTVAL('monitoring_schema.query_id_seq') PRIMARY KEY NOT NULL ,
  name      VARCHAR(200)    NOT NULL,
  statement VARCHAR,
  code_name VARCHAR(100),
  dbms_type_id INT
);

CREATE sequence  monitoring_schema.schedule_id_seq;
CREATE TABLE monitoring_schema.schedule (
  id                  INT DEFAULT NEXTVAL('monitoring_schema.schedule_id_seq') PRIMARY KEY NOT NULL ,
  query_id            INT REFERENCES monitoring_schema.query ON DELETE CASCADE ON UPDATE CASCADE,
  interval_in_seconds INT             NOT NULL
);

CREATE TABLE monitoring_schema.condition_sign (
  id             INT PRIMARY KEY NOT NULL,
  condition_sign VARCHAR(5)      NOT NULL UNIQUE
);
INSERT INTO monitoring_schema.condition_sign (id, condition_sign) VALUES
  (1, '<'),
  (2, '<='),
  (3, '>='),
  (4, '>'),
  (5, '!='),
  (6, '='),
  (7, 'like');

CREATE sequence  monitoring_schema.condition_id_seq;
CREATE TABLE monitoring_schema.condition (
  id                      INT DEFAULT NEXTVAL('monitoring_schema.condition_id_seq') PRIMARY KEY NOT NULL  ,
  condition_sign_id       INT REFERENCES monitoring_schema.condition_sign ON DELETE CASCADE ON UPDATE CASCADE,
  condition_value         VARCHAR(200)    NOT NULL,
  query_id                INT REFERENCES monitoring_schema.query ON DELETE CASCADE ON UPDATE CASCADE,
  alert_text              VARCHAR(200)    NOT NULL,
  is_alert_already_sent   INT DEFAULT 0 NOT NULL
);

CREATE TABLE monitoring_schema.user (
  id         INT PRIMARY KEY NOT NULL,
  nick       VARCHAR(50),
  first_name VARCHAR(50),
  last_name  VARCHAR(100)
);
