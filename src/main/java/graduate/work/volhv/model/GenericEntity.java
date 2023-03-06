package graduate.work.volhv.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GenericEntity {
    private String tableName;
    //key=column name; value=type (eg. Long, String...)
    private Map<String, Class<?>> typeMap = new HashMap<>();
    //key=column name; value=value (eg. 14, "fuck u"...)
    private Map<String, Object> valueMap = new HashMap<>();
}
