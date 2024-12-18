package com.example.csvuploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CsvUploaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CsvUploaderApplication.class, args);
    }
}