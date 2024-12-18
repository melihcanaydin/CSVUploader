package com.example.csvuploader.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvuploader.entity.CsvRecord;
import com.example.csvuploader.repository.CsvRecordRepository;
import com.example.csvuploader.util.CsvParserUtil;
import com.example.csvuploader.util.CsvValidator;

import org.slf4j.Logger;

import jakarta.transaction.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class CsvService {

    @Autowired
    private CsvRecordRepository csvRecordRepository;

    private static final Logger logger = LoggerFactory.getLogger(CsvService.class);

    @Transactional
    public void uploadCsv(MultipartFile file) {
        if (file.isEmpty()) {
            logger.warn("Upload attempt with an empty file.");
            throw new IllegalArgumentException("The uploaded file is empty.");
        }

        logger.info("Starting CSV upload process.");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            List<CsvRecord> records = CsvParserUtil.parseCsv(reader);

            CsvValidator.validateUniqueCodes(records, csvRecordRepository);

            csvRecordRepository.saveAll(records);
            logger.info("Successfully saved {} records to the database.", records.size());
        } catch (Exception e) {
            logger.error("Error processing CSV file: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process the CSV file: " + e.getMessage(), e);
        }
    }

    public List<CsvRecord> getAllRecords() {
        logger.info("Fetching all records from the database.");
        List<CsvRecord> records = csvRecordRepository.findAll();
        if (records.isEmpty()) {
            logger.info("No records found in the database.");
        }
        return records;
    }

    public CsvRecord getRecordByCode(String code) {
        logger.info("Fetching record with code: {}", code);
        Optional<CsvRecord> record = csvRecordRepository.findById(code);
        if (record.isEmpty()) {
            logger.warn("Record with code {} not found.", code);
            throw new IllegalArgumentException("Record with code " + code + " not found.");
        }
        return record.get();
    }

    @Transactional
    public void deleteAllRecords() {
        logger.info("Deleting all records from the database.");
        long count = csvRecordRepository.count();
        csvRecordRepository.deleteAll();
        logger.info("Successfully deleted {} records from the database.", count);
    }
}