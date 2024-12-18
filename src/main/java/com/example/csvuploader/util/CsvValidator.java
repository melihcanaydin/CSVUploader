package com.example.csvuploader.util;

import com.example.csvuploader.entity.CsvRecord;
import com.example.csvuploader.repository.CsvRecordRepository;

import java.util.List;

public class CsvValidator {

    public static void validateUniqueCodes(List<CsvRecord> records, CsvRecordRepository repository) {
        for (CsvRecord record : records) {
            if (repository.existsById(record.getCode())) {
                throw new IllegalArgumentException("Duplicate code found: " + record.getCode());
            }
        }
    }

    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be empty.");
        }
    }
}