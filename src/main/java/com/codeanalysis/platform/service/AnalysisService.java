package com.codeanalysis.platform.service;

import com.codeanalysis.platform.model.AnalysisRequest;
import com.codeanalysis.platform.model.AnalysisResult;
import org.springframework.stereotype.Service;

import com.codeanalysis.platform.model.ScanRecord;
import com.codeanalysis.platform.repository.ScanRecordRepo;

import java.util.List;

@Service
public class AnalysisService {
    private final ScanRecordRepo repository;

    public AnalysisService(ScanRecordRepo repository) {
        this.repository = repository;
    }

    public AnalysisResult analyze(AnalysisRequest request) {
        String code = request.getCode();
        String fileName = request.getFileName();

        int lineCount = countLines(code);
        int methodCount = countMethods(code);
        int importCount = countImports(code);
        String complexity = assessComplexity(methodCount, lineCount);

        // Saving to database
        ScanRecord record = new ScanRecord(fileName, code, methodCount, lineCount, importCount, complexity);
        repository.save(record);

        return new AnalysisResult(fileName, methodCount, lineCount, importCount, complexity);
    }

    public List<ScanRecord> getAllScans() {
        return repository.findAll();
    }

    public List<ScanRecord> getScansByFileName(String fileName) {
        return repository.findByFileName(fileName);
    }

    private int countLines(String code) {
        if (code == null || code.isEmpty())
            return 0;
        return code.split("\n").length;
    }

    private int countMethods(String code) {
        if (code == null || code.isEmpty())
            return 0;
        int count = 0;
        for (String line : code.split("\n")) {
            String trimmed = line.trim();
            if (trimmed.contains("(")
                    && trimmed.contains(")")
                    && trimmed.endsWith("{")
                    && !trimmed.startsWith("if")
                    && !trimmed.startsWith("for")
                    && !trimmed.startsWith("while")
                    && !trimmed.startsWith("class")) {
                count++;
            }
        }
        return count;
    }

    private int countImports(String code) {
        if (code == null || code.isEmpty())
            return 0;
        int count = 0;
        for (String line : code.split("\n")) {
            if (line.trim().startsWith("import ")) {
                count++;
            }
        }
        return count;
    }

    private String assessComplexity(int methodCount, int lineCount) {
        if (lineCount > 200 || methodCount > 15)
            return "HIGH";
        if (lineCount > 100 || methodCount > 8)
            return "MEDIUM";
        return "LOW";
    }
}