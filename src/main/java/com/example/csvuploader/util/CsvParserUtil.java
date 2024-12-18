package com.example.csvuploader.util;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.example.csvuploader.entity.CsvRecord;

public class CsvParserUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static List<CsvRecord> parseCsv(BufferedReader reader) throws Exception {
        List<CsvRecord> records = new ArrayList<>();
        try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim())) {
            for (CSVRecord record : csvParser) {
                CsvRecord csvRecord = new CsvRecord();
                csvRecord.setSource(record.get("source"));
                csvRecord.setCodeListCode(record.get("codeListCode"));
                csvRecord.setCode(record.get("code"));
                csvRecord.setDisplayValue(record.get("displayValue"));
                csvRecord.setLongDescription(record.get("longDescription"));
                csvRecord.setFromDate(dateFormat.parse(record.get("fromDate")));
                String toDate = record.get("toDate");
                csvRecord.setToDate(toDate.isEmpty() ? null : dateFormat.parse(toDate));
                csvRecord.setSortingPriority(record.get("sortingPriority").isEmpty() ? null : Double.valueOf(record.get("sortingPriority")));
                records.add(csvRecord);
            }
        }
        return records;
    }
}