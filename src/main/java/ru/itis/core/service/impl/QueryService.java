package ru.itis.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.itis.core.constants.CommonConstants;
import ru.itis.core.entities.Query;
import ru.itis.core.service.IQueryService;

import java.util.List;

/**
 * Created by Alex on 22.12.16.
 */
@Service
public class QueryService implements IQueryService {

    @Autowired
    private ConfiguredDatabasesService configuredDatabasesService;

    @Override
    public List<Query> getAllQueries(Long databaseId) {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        List<Query> queries = jdbcTemplate.query(CommonConstants.GET_ALL_QUERIES, new BeanPropertyRowMapper(Query.class));
        return queries;
    }

    @Override
    public Query getQuery(Long queryId, Long databaseId) {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        Query query = (Query) jdbcTemplate.queryForObject(CommonConstants.GET_QUERY_BY_ID, new BeanPropertyRowMapper(Query.class), queryId);
        return query;
    }

    @Override
    public void storeNewQuery(Query query, Long databaseId) {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        long queryId = getLastQueryId(jdbcTemplate);
        queryId++;
        jdbcTemplate.update(CommonConstants.SAVE_NEW_QUERY, queryId, query.getName(), query.getStatement());
    }

    @Override
    public void deleteStoredQuery(Long queryId, Long databaseId) {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        jdbcTemplate.update(CommonConstants.DELETE_QUERY_BY_ID, queryId);
    }

    private long getLastQueryId(JdbcTemplate jdbcTemplate) {
        Long id = jdbcTemplate.queryForObject(CommonConstants.GET_MAX_QUERY_ID, Long.class);
        return id == null ? 0 : id;
    }
}
