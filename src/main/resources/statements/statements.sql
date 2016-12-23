INSERT INTO monitoring_schema.query (name, statement, code_name, dbms_type_id) VALUES ('Размер текущей базы данных в байтах', 'SELECT pg_database_size((select current_database())) AS db_size', 'current.db.size', (SELECT id FROM monitoring_schema.dbms_type WHERE dbms_type = 'postgresql' ));

INSERT INTO monitoring_schema.condition ( condition_sign_id, condition_value, query_id, alert_text) VALUES ((SELECT id FROM monitoring_schema.condition_sign WHERE condition_sign = '>='), '10485760', (SELECT id FROM monitoring_schema.query WHERE code_name = 'current.db.size' ), 'Размер текущей базы данных больше 10 Мегабайт' );

INSERT INTO  monitoring_schema.schedule    (interval_in_seconds, query_id) VALUES (20, (SELECT id FROM monitoring_schema.query WHERE code_name = 'current.db.size' ));

---------


INSERT INTO monitoring_schema.query (name, statement, code_name, dbms_type_id) VALUES ('Наличие deadlock`ов', '
             SELECT count(*)
   FROM  pg_catalog.pg_locks         blocked_locks
    JOIN pg_catalog.pg_stat_activity blocked_activity  ON blocked_activity.pid = blocked_locks.pid
    JOIN pg_catalog.pg_locks         blocking_locks 
        ON blocking_locks.locktype = blocked_locks.locktype
        AND blocking_locks.DATABASE IS NOT DISTINCT FROM blocked_locks.DATABASE
        AND blocking_locks.relation IS NOT DISTINCT FROM blocked_locks.relation
        AND blocking_locks.page IS NOT DISTINCT FROM blocked_locks.page
        AND blocking_locks.tuple IS NOT DISTINCT FROM blocked_locks.tuple
        AND blocking_locks.virtualxid IS NOT DISTINCT FROM blocked_locks.virtualxid
        AND blocking_locks.transactionid IS NOT DISTINCT FROM blocked_locks.transactionid
        AND blocking_locks.classid IS NOT DISTINCT FROM blocked_locks.classid
        AND blocking_locks.objid IS NOT DISTINCT FROM blocked_locks.objid
        AND blocking_locks.objsubid IS NOT DISTINCT FROM blocked_locks.objsubid
        AND blocking_locks.pid != blocked_locks.pid
 
    JOIN pg_catalog.pg_stat_activity blocking_activity ON blocking_activity.pid = blocking_locks.pid
   WHERE NOT blocked_locks.GRANTED;
        ', 'is.deadlock.exist', (SELECT id FROM monitoring_schema.dbms_type WHERE dbms_type = 'postgresql' ));

INSERT INTO monitoring_schema.condition ( condition_sign_id, condition_value, query_id, alert_text) VALUES ((SELECT id FROM monitoring_schema.condition_sign WHERE condition_sign = '!='), '0', (SELECT id FROM monitoring_schema.query WHERE code_name = 'is.deadlock.exist' ), 'В системе обнаружен deadlock' );

INSERT INTO  monitoring_schema.schedule    (interval_in_seconds, query_id) VALUES (10, (SELECT id FROM monitoring_schema.query WHERE code_name = 'is.deadlock.exist' ));


