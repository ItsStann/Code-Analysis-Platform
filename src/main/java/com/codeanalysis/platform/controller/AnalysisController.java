package com.codeanalysis.platform.controller;

import com.codeanalysis.platform.model.AnalysisRequest;
import com.codeanalysis.platform.model.AnalysisResult;
import com.codeanalysis.platform.service.AnalysisService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.codeanalysis.platform.model.ScanRecord;

//Sets Rest API for SpringBoot
@RestController
// Base URL Path for every endpoint
@RequestMapping("/api/analysis")
public class AnalysisController {
    private final AnalysisService analysisService;

    AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    // Handles POST request to /api/analysis/scan
    @PostMapping("/scan")
    public AnalysisResult scan(@RequestBody AnalysisRequest request) {
        return analysisService.analyze(request);
    }

    // Handles GET request to /api/analysis/status
    @GetMapping("/status")
    public String status() {
        return "Analysis service is running";
    }

    // Handles GET request to /api/analysis/history
    @GetMapping("/history")
    public List<ScanRecord> getAllScans() {
        return analysisService.getAllScans();
    }

    // Handles GET request to /api/analysis/history/{fileName}
    @GetMapping("/history/{fileName}")
    public List<ScanRecord> getScansByFileName(@PathVariable String fileName) {
        return analysisService.getScansByFileName(fileName);
    }
}
