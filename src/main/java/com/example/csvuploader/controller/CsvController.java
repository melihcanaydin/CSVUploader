package com.example.csvuploader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvuploader.entity.CsvRecord;
import com.example.csvuploader.service.CsvService;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        csvService.uploadCsv(file);
        return ResponseEntity.ok("CSV uploaded successfully.");
    }

    @GetMapping
    public ResponseEntity<List<CsvRecord>> getAllRecords() {
        List<CsvRecord> records = csvService.getAllRecords();
        if (records.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CsvRecord> getRecordByCode(@PathVariable String code) {
        CsvRecord record = csvService.getRecordByCode(code);
        if (record == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(record);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllRecords() {
        csvService.deleteAllRecords();
        return ResponseEntity.ok("All records deleted successfully.");
    }
}