package ru.itis.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.itis.core.entities.Query;
import ru.itis.core.service.IQueryService;
import ru.itis.telegram.IDatabaseService;
import ru.itis.telegram.exception.DoTaskException;

import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 21.12.16.
 */
@Service
public class DatabaseService implements IDatabaseService {

    @Autowired(required = false)
    private IQueryService queryService;

    @Autowired
    private ConfiguredDatabasesService configuredDatabasesService;

    @Override
    public List<Query> getQueries(Long database) throws DoTaskException {
        return queryService.getAllQueries();
    }

    @Override
    public String runCustomQuery(String query, Long database) throws DoTaskException {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(database);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        return getResult(rows);
    }

    @Override
    public String runStoredQuery(Long id, Long database) throws DoTaskException {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(database);
        String query = queryService.getQuery(id, database);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        return getResult(rows);
    }

    @Override
    public void storeNewQuery(String text, Long database) throws DoTaskException {
        queryService.storeNewQuery(text, database);
    }

    @Override
    public void deleteStoredQuery(Long id, Long database) throws DoTaskException {
        queryService.deleteStoredQuery(id, database);
    }

    @Override
    public Query getQuery(Long id, Long database) throws DoTaskException {
        return null;
    }

    @Override
    public void addIfNotExist(Long id, String username, String firstName, String lastName) throws DoTaskException {

    }

    private String getResult(List<Map<String, Object>> rows)  {
        StringBuilder sb =  new StringBuilder();
        sb.append("\n");
        int counter = 1;
        for (Map row : rows) {
            sb.append(counter).append(") ");
            row.entrySet().forEach(value -> sb.append(value).append("\n"));
            sb.append("\n\n");
            counter++;
        }
        return sb.toString();
    }
}
