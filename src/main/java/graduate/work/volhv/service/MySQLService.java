package graduate.work.volhv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@Service
public class MySQLService implements DataBaseService{

    private final DataSource mySQLDataSource;
    private final MetaDataExtractorService metaDataExtractorService;
    private DatabaseMetaData databaseMetaData;


    @Autowired
    public MySQLService(@Qualifier("mySQLDataSource") DataSource mySQLDataSource, MetaDataExtractorService metaDataExtractorService) throws SQLException {
        this.mySQLDataSource = mySQLDataSource;
        this.metaDataExtractorService = metaDataExtractorService;
    }

    public String extractTableInfo() throws SQLException {
        return metaDataExtractorService.extractTableInfo(databaseMetaData);
    }

    public String extractSystemTables() throws SQLException {
        return metaDataExtractorService.extractSystemTables(databaseMetaData);
    }

    public String extractViews() throws SQLException {
        return metaDataExtractorService.extractViews(databaseMetaData);
    }

    public String extractColumnInfo(String tableName) throws SQLException {
        return metaDataExtractorService.extractColumnInfo(databaseMetaData, tableName);
    }

    public String extractPrimaryKeys(String tableName) throws SQLException {
        return metaDataExtractorService.extractPrimaryKeys(databaseMetaData, tableName);
    }

    public String extractForeignKeys(String tableName) throws SQLException {
        return metaDataExtractorService.extractForeignKeys(databaseMetaData, tableName);
    }

    public String extractDatabaseInfo() throws SQLException {
        return metaDataExtractorService.extractDatabaseInfo(databaseMetaData);
    }

    public String extractUserName() throws SQLException {
        return metaDataExtractorService.extractUserName(databaseMetaData);
    }

    public String extractSupportedFeatures() throws SQLException {
        return metaDataExtractorService.extractSupportedFeatures(databaseMetaData);
    }

    @PostConstruct
    private void postConstruct() throws SQLException {
        databaseMetaData = mySQLDataSource.getConnection().getMetaData();
    }
}
