package com.example.volhv.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
//todo: type -- Enum
public class GenericEntity {
    private String tableName;
    //key=column name; value=type (eg. Long, String...)
    private Map<String, Class<?>> typeMap = new HashMap<>();
    //key=column name; value=value (eg. 14, "fuck u"...)
    private Map<String, Object> valueMap = new HashMap<>();
}
