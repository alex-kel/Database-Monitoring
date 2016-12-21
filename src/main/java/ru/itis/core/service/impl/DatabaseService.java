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

    @Autowired
    private IQueryService queryService;

    @Override
    public List<Query> getQueries() throws DoTaskException {
        return queryService.getAllQueries();
    }

    @Override
    public String runCustomQuery(String query) throws DoTaskException {
        return null;
    }

    @Override
    public String runStoredQuery(Long id) throws DoTaskException {
        return null;
    }

    @Override
    public void storeNewQuery(String text) throws DoTaskException {

    }

    @Override
    public void deleteStoredQuery(Long id) throws DoTaskException {

    }

    @Override
    public Query getQuery(Long id) throws DoTaskException {
        return null;
    }

    @Override
    public void addIfNotExist(Long id, String username, String firstName, String lastName) throws DoTaskException {

    }
}
