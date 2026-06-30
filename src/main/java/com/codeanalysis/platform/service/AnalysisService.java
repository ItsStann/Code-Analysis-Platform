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
    private final CodeAnalyzer analyzer;

    public AnalysisService(ScanRecordRepo repository, CodeAnalyzer analyzer) {
        this.repository = repository;
        this.analyzer = analyzer;
    }

    public AnalysisResult analyze(AnalysisRequest request) {
        AnalysisResult result = analyzer.analyze(request.getCode(), request.getFileName());

        ScanRecord record = new ScanRecord(
                request.getFileName(), request.getCode(),
                result.getMethodCount(), result.getLineCount(),
                result.getImportCount(), result.getComplexityLevel());
        // Saves to database
        repository.save(record);

        return result;
    }

    public List<ScanRecord> getAllScans() {
        return repository.findAll();
    }

    public List<ScanRecord> getScansByFileName(String fileName) {
        return repository.findByFileName(fileName);
    }
}