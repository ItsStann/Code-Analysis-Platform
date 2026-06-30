package com.codeanalysis.platform.service;

import com.codeanalysis.platform.model.AnalysisResult;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.*;

@Component
public class CodeAnalyzer {
    // Matches method signatures
    private static final Pattern METHOD_PATTERN = Pattern.compile(
            "^\\\\s*(public|private|protected)\\\\s+(static\\\\s+)?(\\\\w+)\\\\s+(\\\\w+)\\\\s*\\\\([^)]*\\\\)\\\\s*(\\\\{)?",
            Pattern.MULTILINE);

    // Matches import statements
    private static final Pattern IMPORT_PATTERN = Pattern.compile(
            "^import\\s+([\\w.]+);",
            Pattern.MULTILINE);

    public AnalysisResult analyze(String code, String fileName) {
        String[] lines = code.split("\n");

        int lineCount = lines.length;
        int importCount = countImports(code);
        List<String> imports = extractImports(code);
        List<String> unusedImports = findUnusedImports(code, imports);
        Map<String, Integer> methodLengths = extractMethodLengths(code, lines);
        int methodCount = methodLengths.size();
        List<String> dupMethods = findDupMethods(code);
        int cyclomaticComplexity = calculateCyclomaticComplexity(code);
        String complexityLevel = assessComplexity(cyclomaticComplexity, methodCount, lineCount);
        String longestMethod = findLongestMethod(methodLengths);
        int longestMethodLines = longestMethod != null ? methodLengths.get(longestMethod) : 0;
        String recommendation = generateRecommendation(cyclomaticComplexity, methodCount, lineCount, dupMethods,
                unusedImports);

        return new AnalysisResult(fileName, methodCount, lineCount, importCount, complexityLevel,
                cyclomaticComplexity, dupMethods, unusedImports, longestMethod, longestMethodLines, recommendation);
    }

    private int countImports(String code) {
        Matcher matcher = IMPORT_PATTERN.matcher(code);
        int count = 0;
        while (matcher.find())
            count++;
        return count;
    }

    private List<String> extractImports(String code) {
        List<String> imports = new ArrayList<>();
        Matcher matcher = IMPORT_PATTERN.matcher(code);
        while (matcher.find()) {
            // Getting class name only
            String fullImport = matcher.group(1);
            String className = fullImport.substring(fullImport.lastIndexOf('.') + 1);
            imports.add(className);
        }
        return imports;
    }

    private List<String> findUnusedImports(String code, List<String> imports) {
        // Removes import lines (thus scanning only code)
        String codeWithoutImports = code.replaceAll("^import.*\n", "");
        List<String> unused = new ArrayList<>();
        for (String className : imports) {
            if (!codeWithoutImports.contains(className)) {
                unused.add(className);
            }
        }

        return unused;
    }

    private Map<String, Integer> extractMethodLengths(String code, String[] lines) {
        Map<String, Integer> methodLengths = new LinkedHashMap<>();
        Matcher matcher = METHOD_PATTERN.matcher(code);

        List<int[]> methodStarts = new ArrayList<>();
        List<String> methodNames = new ArrayList<>();

        while (matcher.find()) {
            // To find the line number for matching
            int lineNumber = getLineNumber(code, matcher.start());
            methodStarts.add(new int[] { lineNumber });
            methodNames.add(matcher.group(4)); // Group 4 is the method name
        }

        // To calculate length of each method from the closing brace
        for (int i = 0; i < methodStarts.size(); i++) {
            int start = methodStarts.get(i)[0];
            int end = i + 1 < methodStarts.size() ? methodStarts.get(i + 1)[0] - 1 : lines.length;
            methodLengths.put(methodNames.get(i), end - start);
        }

        return methodLengths;
    }

    private int getLineNumber(String code, int charPosition) {
        int line = 1;
        for (int i = 0; i < charPosition && i < code.length(); i++) {
            if (code.charAt(i) == '\n')
                line++;
        }
        return line;
    }

    private List<String> findDupMethods(String code) {
        List<String> allNames = new ArrayList<>();
        List<String> duplicates = new ArrayList<>();
        Matcher matcher = METHOD_PATTERN.matcher(code);

        while (matcher.find()) {
            String name = matcher.group(4);
            if (allNames.contains(name) && !duplicates.contains(name)) {
                duplicates.add(name);
            }
            allNames.add(name);
        }
        return duplicates;
    }

    private int calculateCyclomaticComplexity(String code) {
        // Starts at 1 and adds 1 for every keyword
        int complexity = 1;
        String[] keywords = { "if", "else if", "for", "while", "case", "catch", "&&", "||", "?" };
        for (String keyword : keywords) {
            int index = 0;
            while ((index = code.indexOf(keyword, index)) != -1) {
                complexity++;
                index += keyword.length();
            }
        }
        return complexity;
    }

    private String assessComplexity(int cyclomaticComplexity, int methodCount, int lineCount) {
        if (cyclomaticComplexity > 10 || lineCount > 200 || methodCount > 15)
            return "HIGH";
        if (cyclomaticComplexity > 5 || lineCount > 100 || methodCount > 8)
            return "MEDIUM";
        return "LOW";
    }

    private String findLongestMethod(Map<String, Integer> methodLengths) {
        return methodLengths.entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private String generateRecommendation(int complexity, int methodCount, int lineCount, List<String> duplicates,
            List<String> unusedImports) {
        List<String> suggestions = new ArrayList<>();

        if (complexity > 10) {
            suggestions.add("High cyclomatic complexity (" + complexity + ") - try breaking down complex methods");
        }
        if (methodCount > 15) {
            suggestions.add("A lot of methods (" + methodCount + ") - try splitting some into multiple classes");
        }
        if (lineCount > 200) {
            suggestions.add("File is large (" + lineCount + " lines) - try refactoring");
        }
        if (!duplicates.isEmpty()) {
            suggestions.add("Duplicate methods were found: " + String.join(", ", duplicates));
        }
        if (!unusedImports.isEmpty()) {
            suggestions.add("Unused imports were found: " + String.join(", ", unusedImports));
        }
        if (suggestions.isEmpty()) {
            return "Code looks clean. No major issues to resolve.";
        }

        return String.join(". ", suggestions);
    }
}
