CREATE SCHEMA monitoring_schema;

CREATE TABLE monitoring_schema.query (
  id        INT PRIMARY KEY NOT NULL,
  name      VARCHAR(200)    NOT NULL,
  statement VARCHAR(1000)   NOT NULL
);

CREATE TABLE monitoring_schema.schedule (
  id                  INT PRIMARY KEY NOT NULL,
  interval_in_seconds INT             NOT NULL,
  query_id            INT REFERENCES monitoring_schema.query ON DELETE CASCADE ON UPDATE CASCADE
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
  (5, 'like');


CREATE TABLE monitoring_schema.condition (
  id                INT PRIMARY KEY NOT NULL,
  condition_sign_id INT REFERENCES monitoring_schema.condition_sign ON DELETE CASCADE ON UPDATE CASCADE,
  condition_value        VARCHAR(200)    NOT NULL,
  query_id          INT REFERENCES monitoring_schema.query ON DELETE CASCADE ON UPDATE CASCADE,
  alert_text        VARCHAR(200)    NOT NULL
);


CREATE TABLE monitoring_schema.user (
  id         INT PRIMARY KEY NOT NULL,
  nick       VARCHAR(50)     NOT NULL,
  first_name VARCHAR(50)     NOT NULL,
  last_name  VARCHAR(100)    NOT NULL
);