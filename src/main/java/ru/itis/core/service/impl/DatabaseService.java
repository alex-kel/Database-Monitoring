package ru.itis.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.core.entities.Query;
import ru.itis.core.service.IQueryService;
import ru.itis.telegram.IDatabaseService;
import ru.itis.telegram.exception.DoTaskException;

import java.util.List;

/**
 * Created by Alex on 21.12.16.
 */
@Service
public class DatabaseService implements IDatabaseService {

    @Autowired(required = false)
    private IQueryService queryService;

    @Override
    public List<Query> getQueries(Long database) throws DoTaskException {
        return queryService.getAllQueries();
    }

    @Override
    public String runCustomQuery(String query, Long database) throws DoTaskException {
        return null;
    }

    @Override
    public String runStoredQuery(Long id, Long database) throws DoTaskException {
        return null;
    }

    @Override
    public void storeNewQuery(String text, Long database) throws DoTaskException {

    }

    @Override
    public void deleteStoredQuery(Long id, Long database) throws DoTaskException {

    }

    @Override
    public Query getQuery(Long id, Long database) throws DoTaskException {
        return null;
    }

    @Override
    public void addIfNotExist(Long id, String username, String firstName, String lastName) throws DoTaskException {

    }
}
