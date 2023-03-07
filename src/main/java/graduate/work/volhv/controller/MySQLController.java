package graduate.work.volhv.controller;

import graduate.work.volhv.service.MySQLService;
import graduate.work.volhv.service.PostgresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/mysql")
public class MySQLController {
    private final MySQLService mySQLService;

    @Autowired
    public MySQLController(MySQLService mySQLService) {
        this.mySQLService = mySQLService;
    }

    @GetMapping("/tables")
    public ResponseEntity<String> extractTableInfo() throws SQLException {
        return new ResponseEntity<>(mySQLService.extractTableInfo(), HttpStatus.OK);

    }

    @GetMapping("/system-tables")
    public ResponseEntity<String> extractSystemTables() throws SQLException {
        return new ResponseEntity<>(mySQLService.extractSystemTables(), HttpStatus.OK);
    }

    @GetMapping("/views")
    public ResponseEntity<String> extractViews() throws SQLException {
        return new ResponseEntity<>(mySQLService.extractViews(), HttpStatus.OK);
    }

    @GetMapping("/tables/{table}/columns")
    public ResponseEntity<String> extractColumnInfo(@PathVariable(name = "table") String tableName) throws SQLException {
        return new ResponseEntity<>(mySQLService.extractColumnInfo(tableName), HttpStatus.OK);
    }

    @GetMapping("/tables/{table}/primary-keys")
    public ResponseEntity<String> extractPrimaryKeys(@PathVariable(name = "table") String tableName) throws SQLException {
        return new ResponseEntity<>(mySQLService.extractPrimaryKeys(tableName), HttpStatus.OK);
    }

    @GetMapping("/tables/{table}/foreign-keys")
    public ResponseEntity<String> extractForeignKeys(@PathVariable(name = "table") String tableName) throws SQLException {
        return new ResponseEntity<>(mySQLService.extractForeignKeys(tableName), HttpStatus.OK);
    }

    @GetMapping("/db-info")
    public ResponseEntity<String> extractDatabaseInfo() throws SQLException {
        return new ResponseEntity<>(mySQLService.extractDatabaseInfo(), HttpStatus.OK);
    }

    @GetMapping("/user-info")
    public ResponseEntity<String> extractUserName() throws SQLException {
        return new ResponseEntity<>(mySQLService.extractUserName(), HttpStatus.OK);
    }

    @GetMapping("/supported-features")
    public ResponseEntity<String> extractSupportedFeatures() throws SQLException {
        return new ResponseEntity<>(mySQLService.extractSupportedFeatures(), HttpStatus.OK);
    }
}
