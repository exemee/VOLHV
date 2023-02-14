package com.example.volhv.controller;

import com.example.volhv.model.GenericEntity;
import com.example.volhv.service.DBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final DBService dbService;

    public MainController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping
    public ResponseEntity<String> getDbInfo() {
        GenericEntity entity = dbService.getDbInfo();
        System.err.println(entity);
        return new ResponseEntity<>("fuck u", HttpStatus.OK);
    }
}
