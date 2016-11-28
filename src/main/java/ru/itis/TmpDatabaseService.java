package ru.itis;

import org.springframework.stereotype.Service;
import ru.itis.core.entities.Query;
import ru.itis.telegram.IDatabaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
@Service
public class TmpDatabaseService implements IDatabaseService{
    @Override
    public List<Query> getQueries() {
        return new ArrayList<>();
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
    public void storeNewQuery(String text) {

    }

    @Override
    public void deleteStoredQuery(Long id) {

    }
}
