package graduate.work.volhv.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Service
public class PostgresService implements DataBaseService {

    private final DataSource postgresDataSource;
    private final MetaDataExtractorService metaDataExtractorService;
    private DatabaseMetaData databaseMetaData;


    @Autowired
    public PostgresService(@Qualifier("postgresDataSource") DataSource postgresDataSource, MetaDataExtractorService metaDataExtractorService) throws SQLException {
        this.postgresDataSource = postgresDataSource;
        this.metaDataExtractorService = metaDataExtractorService;
    }

    public String extractTableInfo() throws SQLException {
        return metaDataExtractorService.extractTableInfo(databaseMetaData);
    }

    public void extractSystemTables() throws SQLException {
        try (ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"SYSTEM TABLE"})) {
            while (resultSet.next()) {
                // Print the names of system tables
                System.out.println(resultSet.getString("TABLE_NAME"));
            }
        }
    }

    public void extractViews() throws SQLException {
        try (ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"VIEW"})) {
            while (resultSet.next()) {
                // Print the names of existing views
                System.out.println(resultSet.getString("TABLE_NAME"));
            }
        }
    }

    public String extractColumnInfo(String tableName) throws SQLException {
        return metaDataExtractorService.extractColumnInfo(databaseMetaData, tableName);
    }

    public void extractPrimaryKeys(DatabaseMetaData databaseMetaData, String tableName) throws SQLException {
        try (ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, tableName)) {
            while (primaryKeys.next()) {
                String primaryKeyColumnName = primaryKeys.getString("COLUMN_NAME");
                String primaryKeyName = primaryKeys.getString("PK_NAME");
                System.out.println(String.format("columnName:%s, pkName:%s", primaryKeyColumnName, primaryKeyName));
            }
        }
    }

    public void extractForeignKeys(DatabaseMetaData databaseMetaData, String tableName) throws SQLException {
        try (ResultSet foreignKeys = databaseMetaData.getImportedKeys(null, null, tableName)) {
            while (foreignKeys.next()) {
                String pkTableName = foreignKeys.getString("PKTABLE_NAME");
                String fkTableName = foreignKeys.getString("FKTABLE_NAME");
                String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");
                String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                System.out.printf("pkTableName:%s, fkTableName:%s, pkColumnName:%s, fkColumnName:%s%n", pkTableName, fkTableName, pkColumnName, fkColumnName);
            }
        }
    }

    public void extractDatabaseInfo() throws SQLException {
        String productName = databaseMetaData.getDatabaseProductName();
        String productVersion = databaseMetaData.getDatabaseProductVersion();

        String driverName = databaseMetaData.getDriverName();
        String driverVersion = databaseMetaData.getDriverVersion();

        System.out.printf("Product name:%s, Product version:%s%n", productName, productVersion);
        System.out.printf("Driver name:%s, Driver Version:%s%n", driverName, driverVersion);
    }

    public void extractUserName() throws SQLException {
        String userName = databaseMetaData.getUserName();
        System.out.println(userName);
        try (ResultSet schemas = databaseMetaData.getSchemas()) {
            while (schemas.next()) {
                String table_schem = schemas.getString("TABLE_SCHEM");
                String table_catalog = schemas.getString("TABLE_CATALOG");
                System.out.printf("Table_schema:%s, Table_catalog:%s%n", table_schem, table_catalog);
            }
        }
    }

    public void extractSupportedFeatures() throws SQLException {
        System.out.println("Supports scrollable & Updatable Result Set: " + databaseMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE));
        System.out.println("Supports Full Outer Joins: " + databaseMetaData.supportsFullOuterJoins());
        System.out.println("Supports Stored Procedures: " + databaseMetaData.supportsStoredProcedures());
        System.out.println("Supports Subqueries in 'EXISTS': " + databaseMetaData.supportsSubqueriesInExists());
        System.out.println("Supports Transactions: " + databaseMetaData.supportsTransactions());
        System.out.println("Supports Core SQL Grammar: " + databaseMetaData.supportsCoreSQLGrammar());
        System.out.println("Supports Batch Updates: " + databaseMetaData.supportsBatchUpdates());
        System.out.println("Supports Column Aliasing: " + databaseMetaData.supportsColumnAliasing());
        System.out.println("Supports Savepoints: " + databaseMetaData.supportsSavepoints());
        System.out.println("Supports Union All: " + databaseMetaData.supportsUnionAll());
        System.out.println("Supports Union: " + databaseMetaData.supportsUnion());
    }

    @PostConstruct
    private void postConstruct() throws SQLException {
        databaseMetaData = postgresDataSource.getConnection().getMetaData();
    }


}
