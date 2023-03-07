package graduate.work.volhv.service;

import graduate.work.volhv.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Service
public class MetaDataExtractorService {

    public String extractTableInfo(DatabaseMetaData databaseMetaData) throws SQLException, DataNotFoundException {
        StringBuilder result = new StringBuilder();
        try (ResultSet resultSet = databaseMetaData.getTables(null, null, "%", new String[] { "TABLE" })) {
            while (resultSet.next()) {
                // Print the names of existing tables
                result.append(resultSet.getString("TABLE_NAME")).append("\n");
            }
        }
        log.info("result: {}", result);
        return String.valueOf(result);
    }

    public String extractSystemTables(DatabaseMetaData databaseMetaData) throws SQLException {
        StringBuilder result = new StringBuilder();
        try (ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] { "SYSTEM TABLE" })) {
            while (resultSet.next()) {
                // Print the names of system tables
                result.append(resultSet.getString("TABLE_NAME")).append("\n");
            }
        }
        log.info("result: {}", result);
        return String.valueOf(result);
    }

    public String extractViews(DatabaseMetaData databaseMetaData) throws SQLException {
        StringBuilder result = new StringBuilder();
        try(ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] { "VIEW" })) {
            while (resultSet.next()) {
                // Print the names of existing views
                result.append(resultSet.getString("TABLE_NAME")).append("\n");
            }
        }
        log.info("result: {}", result);
        return String.valueOf(result);
    }

    public String extractColumnInfo(DatabaseMetaData databaseMetaData, String tableName) throws SQLException, DataNotFoundException {
        StringBuilder result = new StringBuilder();
        try (ResultSet columns = databaseMetaData.getColumns(null, null, tableName, null)) {
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnSize = columns.getString("COLUMN_SIZE");
                String datatype = columns.getString("DATA_TYPE");
                String isNullable = columns.getString("IS_NULLABLE");
                String isAutoIncrement = columns.getString("IS_AUTOINCREMENT");
                result.append(String.format("ColumnName: %s, columnSize: %s, datatype: %s, isColumnNullable: %s, isAutoIncrementEnabled: %s\n", columnName, columnSize, datatype, isNullable, isAutoIncrement));
            }
        }
        log.info("result: {}", result);
        return String.valueOf(result);
    }

    public String extractPrimaryKeys(DatabaseMetaData databaseMetaData, String tableName) throws SQLException, DataNotFoundException {
        StringBuilder result = new StringBuilder();
        try(ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, tableName)) {
            while (primaryKeys.next()) {
                String primaryKeyColumnName = primaryKeys.getString("COLUMN_NAME");
                String primaryKeyName = primaryKeys.getString("PK_NAME");
                result.append(String.format("columnName:%s, pkName:%s", primaryKeyColumnName, primaryKeyName)).append("\n");
            }
        }
        log.info("result: {}", result);
        return String.valueOf(result);
    }

    public String extractForeignKeys(DatabaseMetaData databaseMetaData, String tableName) throws SQLException {
        StringBuilder result = new StringBuilder();
        try(ResultSet foreignKeys = databaseMetaData.getImportedKeys(null, null, tableName)) {
            while (foreignKeys.next()) {
                String pkTableName = foreignKeys.getString("PKTABLE_NAME");
                String fkTableName = foreignKeys.getString("FKTABLE_NAME");
                String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");
                String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                result.append(String.format("pkTableName:%s, fkTableName:%s, pkColumnName:%s, fkColumnName:%s%n", pkTableName, fkTableName, pkColumnName, fkColumnName)).append("\n");
            }
        }
        log.info("result: {}", result);
        return String.valueOf(result);
    }

    public String extractDatabaseInfo(DatabaseMetaData databaseMetaData) throws SQLException {
        StringBuilder result = new StringBuilder();

        String productName = databaseMetaData.getDatabaseProductName();
        String productVersion = databaseMetaData.getDatabaseProductVersion();

        String driverName = databaseMetaData.getDriverName();
        String driverVersion = databaseMetaData.getDriverVersion();

        result.append(String.format("Product name:%s, Product version:%s%n", productName, productVersion)).append("\n");
        result.append(String.format("Driver name:%s, Driver Version:%s%n", driverName, driverVersion)).append("\n");

        log.info("result: {}", result);
        return String.valueOf(result);
    }

    public String extractUserName(DatabaseMetaData databaseMetaData) throws SQLException {
        StringBuilder result = new StringBuilder();
        String userName = databaseMetaData.getUserName();
        result.append(String.format("User name:%s", userName)).append("\n");
        try(ResultSet schemas = databaseMetaData.getSchemas()) {
            while (schemas.next()) {
                String table_schem = schemas.getString("TABLE_SCHEM");
                String table_catalog = schemas.getString("TABLE_CATALOG");
                result.append(String.format("Table_schema:%s, Table_catalog:%s%n", table_schem, table_catalog)).append("\n");
            }
        }
        log.info("result: {}", result);
        return String.valueOf(result);
    }

    public String extractSupportedFeatures(DatabaseMetaData databaseMetaData) throws SQLException {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Supports scrollable & Updatable Result Set: " + databaseMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE))).append("\n");
        result.append(String.format("Supports Full Outer Joins: " + databaseMetaData.supportsFullOuterJoins())).append("\n");
        result.append(String.format("Supports Stored Procedures: " + databaseMetaData.supportsStoredProcedures())).append("\n");
        result.append(String.format("Supports Subqueries in 'EXISTS': " + databaseMetaData.supportsSubqueriesInExists())).append("\n");
        result.append(String.format("Supports Transactions: " + databaseMetaData.supportsTransactions())).append("\n");
        result.append(String.format("Supports Core SQL Grammar: " + databaseMetaData.supportsCoreSQLGrammar())).append("\n");
        result.append(String.format("Supports Batch Updates: " + databaseMetaData.supportsBatchUpdates())).append("\n");
        result.append(String.format("Supports Column Aliasing: " + databaseMetaData.supportsColumnAliasing())).append("\n");
        result.append(String.format("Supports Savepoints: " + databaseMetaData.supportsSavepoints())).append("\n");
        result.append(String.format("Supports Union All: " + databaseMetaData.supportsUnionAll())).append("\n");
        result.append(String.format("Supports Union: " + databaseMetaData.supportsUnion())).append("\n");

        log.info("result: {}", result);
        return String.valueOf(result);
    }
}
