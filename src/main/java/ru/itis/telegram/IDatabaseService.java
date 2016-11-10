package ru.itis.telegram;

import ru.itis.entities.Query;

import java.util.List;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
public interface IDatabaseService {

    List<Query> getQueries();

    String runCustomQuery(String query);

    String runStoredQuery(Long id);

    void storeNewQuery(String text);

    void deleteStoredQuery(Long id);
}
