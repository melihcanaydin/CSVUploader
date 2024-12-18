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
                csvRecord.setSource(record.get(CsvConstants.SOURCE));
                csvRecord.setCodeListCode(record.get(CsvConstants.CODE_LIST_CODE));
                csvRecord.setCode(record.get(CsvConstants.CODE));
                csvRecord.setDisplayValue(record.get(CsvConstants.DISPLAY_VALUE));
                csvRecord.setLongDescription(record.get(CsvConstants.LONG_DESCRIPTION));
                csvRecord.setFromDate(dateFormat.parse(record.get(CsvConstants.FROM_DATE)));
                String toDate = record.get(CsvConstants.TO_DATE);
                csvRecord.setToDate(toDate.isEmpty() ? null : dateFormat.parse(toDate));
                csvRecord.setSortingPriority(record.get(CsvConstants.SORTING_PRIORITY).isEmpty() ? null
                        : Double.valueOf(record.get(CsvConstants.SORTING_PRIORITY)));
                records.add(csvRecord);
            }
        }
        return records;
    }
}