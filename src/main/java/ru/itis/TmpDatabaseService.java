package ru.itis;

import org.springframework.stereotype.Service;
import ru.itis.core.entities.Query;
import ru.itis.telegram.IDatabaseService;
import ru.itis.telegram.exception.DoTaskException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Service
public class TmpDatabaseService implements IDatabaseService{
    @Override
    public List<Query> getQueries() {
        List<Query> queries = new ArrayList<>();
        for (long i = 0; i < 10; i++ ){
            Query query = createQuery(i);
            queries.add(query);
        }
        return queries;
    }

    private Query createQuery(long i) {
        Query query = new Query();
        query.setId(i);
        query.setName("name" + i);
        query.setStatement("statement" + i);
        return query;
    }

    @Override
    public String runCustomQuery(String query) {
        return "";
    }

    @Override
    public String runStoredQuery(Long id) {
        return "";
    }

    @Override
    public void storeNewQuery(String text) throws DoTaskException{
    }

    @Override
    public void deleteStoredQuery(Long id) {
    }

    @Override
    public Query getQuery(Long id) {
        return createQuery(id);
    }
}
