package com.example.volhv.service;

import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class DataBaseMetaDataResolver implements DatabaseMetaDataCallback {
    @Override
    public Object processMetaData(DatabaseMetaData dbmd) throws SQLException{
        ResultSet rs = dbmd.getTables(dbmd.getUserName(), null, null, new String[]{"TABLE"});
        ArrayList<String> tables = new ArrayList<>();
        while (rs.next()) {
            tables.add(rs.getString(3));
        }
        return tables;
    }
}
