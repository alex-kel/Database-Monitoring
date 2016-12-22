package ru.itis.core.service;

import ru.itis.core.entities.Query;

import java.util.List;

/**
 * Created by Alex on 21.12.16.
 */
public interface IQueryService {

    List<Query> getAllQueries();

    String getQuery(Long queryId, Long databaseId);

    void storeNewQuery(String query, Long databaseId);

    void deleteStoredQuery(Long queryId, Long databaseId);
}
