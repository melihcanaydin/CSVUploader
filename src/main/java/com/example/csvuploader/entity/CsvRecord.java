package com.example.csvuploader.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class CsvRecord {

    @Id
    private String code;
    private String source;
    private String codeListCode;
    private String displayValue;
    private String longDescription;
    private Date fromDate;
    private Date toDate;
    private Double sortingPriority;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getCodeListCode() {
        return codeListCode;
    }
    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }
    public String getDisplayValue() {
        return displayValue;
    }
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
    public String getLongDescription() {
        return longDescription;
    }
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    public Date getFromDate() {
        return fromDate;
    }
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    public Date getToDate() {
        return toDate;
    }
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
    public Double getSortingPriority() {
        return sortingPriority;
    }
    public void setSortingPriority(Double sortingPriority) {
        this.sortingPriority = sortingPriority;
    }

    
}