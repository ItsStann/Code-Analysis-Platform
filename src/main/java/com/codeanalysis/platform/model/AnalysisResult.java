package com.codeanalysis.platform.model;

public class AnalysisResult {
    private String fileName;
    private int methodCount;
    private int lineCount;
    private int importCount;
    private String complexityLevel;

    public AnalysisResult() {
    }

    public AnalysisResult(String fileName, int methodCount,
            int lineCount, int importCount,
            String complexityLevel) {
        this.fileName = fileName;
        this.methodCount = methodCount;
        this.lineCount = lineCount;
        this.importCount = importCount;
        this.complexityLevel = complexityLevel;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

}
