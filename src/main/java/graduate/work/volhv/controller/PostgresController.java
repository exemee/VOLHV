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
    public PostgresController(PostgresService postgresService, MySQLService mySQLService) {
        this.postgresService = postgresService;
    }

    @GetMapping("/tables")
    public ResponseEntity<String> extractTableInfo() throws SQLException {
        return new ResponseEntity<>(postgresService.extractTableInfo(), HttpStatus.OK);

    }

    @GetMapping("/tables/{table}/columns")
    public ResponseEntity<String> extractColumnInfo(@PathVariable(name = "table") String tableName) throws SQLException {
        return new ResponseEntity<>(postgresService.extractColumnInfo(tableName), HttpStatus.OK);
    }
}
