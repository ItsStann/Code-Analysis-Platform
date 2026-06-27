package com.codeanalysis.platform.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scan_records")
public class ScanRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(columnDefinition = "TEXT")
    private String code;

    private int methodCount;
    private int lineCount;
    private int importCount;
    private String complexityLevel;

    @Column(nullable = false)
    private LocalDateTime scannedTime;

    public ScanRecord() {
    }

    public ScanRecord(String fileName, String code, int methodCount,
            int lineCount, int importCount,
            String complexityLevel) {
        this.fileName = fileName;
        this.code = code;
        this.methodCount = methodCount;
        this.lineCount = lineCount;
        this.importCount = importCount;
        this.complexityLevel = complexityLevel;
        this.scannedTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(int methodCount) {
        this.methodCount = methodCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public int getImportCount() {
        return importCount;
    }

    public void setImportCount(int importCount) {
        this.importCount = importCount;
    }

    public String getComplexityLevel() {
        return complexityLevel;
    }

    public void setComplexityLevel(String complexityLevel) {
        this.complexityLevel = complexityLevel;
    }

    public LocalDateTime getScannedAt() {
        return scannedTime;
    }

    public void setScannedAt(LocalDateTime scannedTime) {
        this.scannedTime = scannedTime;
    }
}
