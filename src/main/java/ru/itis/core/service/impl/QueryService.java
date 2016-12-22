package ru.itis.core.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.core.entities.Query;
import ru.itis.core.service.IQueryService;

import java.util.List;

/**
 * Created by Alex on 22.12.16.
 */
@Service
public class QueryService implements IQueryService {

    @Override
    public List<Query> getAllQueries() {
        return null;
    }

    @Override
    public Query getQuery(Long queryId, Long databaseId) {
        return null;
    }

    @Override
    public void storeNewQuery(Query query, Long databaseId) {

    }

    @Override
    public void deleteStoredQuery(Long queryId, Long databaseId) {

    }
}
