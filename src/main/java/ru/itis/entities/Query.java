package ru.itis.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alex on 14.10.16.
 */

@Entity
@Table(schema = "DBMonitoring")
public class Query implements Serializable{

    @Column
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column
    private String name;

    @Column
    private String statement;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private QueryResultType resultType;

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

    public QueryResultType getResultType() {
        return resultType;
    }

    public void setResultType(QueryResultType resultType) {
        this.resultType = resultType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query query = (Query) o;

        if (!id.equals(query.id)) return false;
        if (!name.equals(query.name)) return false;
        if (!statement.equals(query.statement)) return false;
        return resultType == query.resultType;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + statement.hashCode();
        result = 31 * result + resultType.hashCode();
        return result;
    }
}
