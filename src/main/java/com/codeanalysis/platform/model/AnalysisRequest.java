package com.codeanalysis.platform.model;

public class AnalysisRequest {
    private String code;
    private String fileName;

    // Default Constructor - To deserialize JSON
    public AnalysisRequest() {
    }

    public AnalysisRequest(String code, String fileName) {
        this.code = code;
        this.fileName = fileName;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
