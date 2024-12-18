package com.example.csvuploader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.csvuploader.entity.CsvRecord;

@Repository
public interface CsvRecordRepository extends JpaRepository<CsvRecord, String> { }