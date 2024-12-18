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
import java.util.ArrayList;
import java.util.List;

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
        return new ArrayList<>();
    }

    public CsvRecord getRecordByCode(String code) {
        return null;
    }

    public void deleteAllRecords() {
    }
}