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

    private String codeName;

    private Integer dbmsTypeId;

    public Query(String name, String statement) {
        this.name = name;
        this.statement = statement;
    }

    public Query(Long id, String name, String statement) {
        this.id = id;
        this.name = name;
        this.statement = statement;
    }

    public Query() {
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

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getDbmsTypeId() {
        return dbmsTypeId;
    }

    public void setDbmsTypeId(Integer dbmsTypeId) {
        this.dbmsTypeId = dbmsTypeId;
    }
}
