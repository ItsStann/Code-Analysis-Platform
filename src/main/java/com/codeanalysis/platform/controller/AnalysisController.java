package com.codeanalysis.platform.controller;

import com.codeanalysis.platform.model.AnalysisRequest;
import com.codeanalysis.platform.model.AnalysisResult;
import com.codeanalysis.platform.service.AnalysisService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {
    private final AnalysisService analysisService;

    AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/scan")
    public AnalysisResult scan(@RequestBody AnalysisRequest request) {
        return analysisService.analyze(request);
    }

    @GetMapping("/status")
    public String status() {
        return "Analysis service is running";
    }

}
