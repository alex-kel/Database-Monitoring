INSERT INTO monitoring_schema.query (name, statement, code_name, dbms_type_id) VALUES ('Размер текущей базы данных в байтах', 'SELECT pg_database_size((select current_database())) AS db_size', 'current.db.size', (SELECT id FROM monitoring_schema.dbms_type WHERE dbms_type = 'postgresql' ));

INSERT INTO monitoring_schema.condition ( condition_sign_id, condition_value, query_id, alert_text) VALUES ((SELECT id FROM monitoring_schema.condition_sign WHERE condition_sign = '<='), '10485760', (SELECT id FROM monitoring_schema.query WHERE code_name = 'current.db.size' ), 'Размер текущей базы данных больше 10 Мегабайт' );

INSERT INTO  monitoring_schema.schedule    (interval_in_seconds, query_id) VALUES (20, (SELECT id FROM monitoring_schema.query WHERE code_name = 'current.db.size' ));

---------


