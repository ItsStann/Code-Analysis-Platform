package com.codeanalysis.platform.model;

import java.util.List;

public class AnalysisResult {
    private String fileName;
    private int methodCount;
    private int lineCount;
    private int importCount;
    private String complexityLevel;
    private int cyclomaticComplexity;
    private List<String> dupMethods;
    private List<String> unusedImports;
    private String longestMethod;
    private int longestMethodLines;
    private String recommendation;

    public AnalysisResult() {
    }

    public AnalysisResult(String fileName, int methodCount,
            int lineCount, int importCount, String complexityLevel,
            int cyclomaticComplexity, List<String> dupMethods,
            List<String> unusedImports, String longestMethod,
            int longestMethodLines, String recommendation) {
        this.fileName = fileName;
        this.methodCount = methodCount;
        this.lineCount = lineCount;
        this.importCount = importCount;
        this.complexityLevel = complexityLevel;
        this.cyclomaticComplexity = cyclomaticComplexity;
        this.dupMethods = dupMethods;
        this.unusedImports = unusedImports;
        this.longestMethod = longestMethod;
        this.longestMethodLines = longestMethodLines;
        this.recommendation = recommendation;
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

    public int getCyclomaticComplexity() {
        return cyclomaticComplexity;
    }

    public void setCyclomaticComplexity(int cyclomaticComplexity) {
        this.cyclomaticComplexity = cyclomaticComplexity;
    }

    public List<String> getDupMethods() {
        return dupMethods;
    }

    public void setDupMethods(List<String> dupMethods) {
        this.dupMethods = dupMethods;
    }

    public List<String> getUnusedImports() {
        return unusedImports;
    }

    public void setUnusedImports(List<String> unusedImports) {
        this.unusedImports = unusedImports;
    }

    public String getLongestMethod() {
        return longestMethod;
    }

    public void setLongestMethod(String longestMethod) {
        this.longestMethod = longestMethod;
    }

    public int getLongestMethodLines() {
        return longestMethodLines;
    }

    public void setLongestMethodLines(int longestMethodLines) {
        this.longestMethodLines = longestMethodLines;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

}
