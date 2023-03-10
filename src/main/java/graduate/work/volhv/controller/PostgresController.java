package graduate.work.volhv.controller;

import graduate.work.volhv.service.MySQLService;
import graduate.work.volhv.service.PostgresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/pg")
public class PostgresController {
    private final PostgresService postgresService;

    @Autowired
    public PostgresController(PostgresService postgresService) {
        this.postgresService = postgresService;
    }

    @GetMapping("/tables")
    public ResponseEntity<String> extractTableInfo() throws SQLException {
        return new ResponseEntity<>(postgresService.extractTableInfo(), HttpStatus.OK);

    }

    @GetMapping("/system-tables")
    public ResponseEntity<String> extractSystemTables() throws SQLException {
        return new ResponseEntity<>(postgresService.extractSystemTables(), HttpStatus.OK);
    }

    @GetMapping("/views")
    public ResponseEntity<String> extractViews() throws SQLException {
        return new ResponseEntity<>(postgresService.extractViews(), HttpStatus.OK);
    }

    @GetMapping("/tables/{table}/columns")
    public ResponseEntity<String> extractColumnInfo(@PathVariable(name = "table") String tableName) throws SQLException {
        return new ResponseEntity<>(postgresService.extractColumnInfo(tableName), HttpStatus.OK);
    }

    @GetMapping("/tables/{table}/primary-keys")
    public ResponseEntity<String> extractPrimaryKeys(@PathVariable(name = "table") String tableName) throws SQLException {
        return new ResponseEntity<>(postgresService.extractPrimaryKeys(tableName), HttpStatus.OK);
    }

    @GetMapping("/tables/{table}/foreign-keys")
    public ResponseEntity<String> extractForeignKeys(@PathVariable(name = "table") String tableName) throws SQLException {
        return new ResponseEntity<>(postgresService.extractForeignKeys(tableName), HttpStatus.OK);
    }

    @GetMapping("/db-info")
    public ResponseEntity<String> extractDatabaseInfo() throws SQLException {
        return new ResponseEntity<>(postgresService.extractDatabaseInfo(), HttpStatus.OK);
    }

    @GetMapping("/user-info")
    public ResponseEntity<String> extractUserName() throws SQLException {
        return new ResponseEntity<>(postgresService.extractUserName(), HttpStatus.OK);
    }

    @GetMapping("/supported-features")
    public ResponseEntity<String> extractSupportedFeatures() throws SQLException {
        return new ResponseEntity<>(postgresService.extractSupportedFeatures(), HttpStatus.OK);
    }
}
