CREATE SCHEMA monitoring_schema;


CREATE TABLE monitoring_schema.query(
   id INT PRIMARY KEY     NOT NULL,
   name           VARCHAR(200)    NOT NULL,
   statement      VARCHAR(1000)     NOT NULL
);


CREATE TABLE monitoring_schema.schedule(
   id INT PRIMARY KEY     NOT NULL,
   interval_in_seconds  int NOT NULL,
   query_id int REFERENCES monitoring_schema.query ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE monitoring_schema.condition(
   id INT PRIMARY KEY     NOT NULL,
   condition_type varchar(50)  NOT NULL,
   query_id int REFERENCES monitoring_schema.query ON DELETE CASCADE ON UPDATE CASCADE,
   alert_text varchar(200)  NOT NULL
);

CREATE TABLE monitoring_schema.user(
   id INT PRIMARY KEY     NOT NULL,
   nick varchar(50),
   first_name varchar(50),
   last_name varchar(100)
);
