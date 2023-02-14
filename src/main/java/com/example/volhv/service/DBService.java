package com.example.volhv.service;

import com.example.volhv.model.GenericEntity;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DBService {

    private final DataBaseMetaDataResolver dataBaseMetaDataResolver;
    private final DataSource dataSource;
    private Map<String, Class<?>> entities;
    private Map<String, Class<?>> repositories;

    public DBService(DataBaseMetaDataResolver dataBaseMetaDataResolver, DataSource dataSource) {
        this.dataBaseMetaDataResolver = dataBaseMetaDataResolver;
        this.dataSource = dataSource;
        this.entities = new HashMap<>();
        this.repositories = new HashMap<>();
    }

    public GenericEntity getDbInfo() {
        try {
            List<String> tableNames = (List<String>) JdbcUtils.extractDatabaseMetaData(dataSource, dataBaseMetaDataResolver);
            tableNames.forEach(System.err::println);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        GenericEntity car = new GenericEntity();
        car.setTableName("car");
        //got name field
        car.getTypeMap().put("name", String.class);
        car.getValueMap().put("name", "volvo");
        //got years field
        car.getTypeMap().put("years", Long.class);
        car.getValueMap().put("years", 2001);
        //got death date field
        car.getTypeMap().put("years", LocalDateTime.class);
        car.getValueMap().put("years", LocalDateTime.now());
        return car;
    }
}
