package ru.itis.core.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alex on 14.10.16.
 */

public class Query implements Serializable{

    private Long id;

    private String name;

    private String statement;

    public Query(String name, String statement) {
        this.name = name;
        this.statement = statement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query query = (Query) o;

        if (!id.equals(query.id)) return false;
        if (!name.equals(query.name)) return false;
        return statement.equals(query.statement);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + statement.hashCode();
        return result;
    }
}
