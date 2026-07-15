# Code Analysis Platform
A full-stack Java web service that performs static analysis on submitted Java code and returns a structured quality report. 
Built with Spring Boot, PostgreSQL, and Docker.

## Additional tools and resources used:
- Maven
- Spring Boot JPA
- Docker Compose

--------------------------------------------------------------------------------------------------------------------------------
## What does it do?
Paste any Java source file or code into the web interface and receive a structured report analysis that covers:
- **Method Count** - total number of methods
- **Line Count** - total lines of code
- **Import Count** - number of import statements
- **Cyclomatic Complexity** - decision score that indicates how complex the code is
- **Complexity Level** - (Low/Medium/High) a rating based on several factors within the code
- **Duplicate Methods** - methods that have the same name
- **Unused Imports** - imports made within the file/code that were not utilized
- **Longest Method** - provides the method with the most lines of code
- **Recommendation** - suggestions for refactoring and lowering the complexity of your code based off the analysis

--------------------------------------------------------------------------------------------------------------------------------
## Tech Stack
**Backend**
- Java, Spring Boot, Spring MVC

**Database**
- PostgreSQL, Spring Data JPA, Hibernate

**Containerization**
- Docker, Docker Compose

**Build Tool**
- Maven

## ▶️ Getting Started
### Prerequisites
- Docker Desktop installed and running

### To Run the App
1. Clone the repository
2. Start the full stack with Docker Compose (docker compose up --build)
3. Open your browser and go to http://localhost:8080

### To Stop the App 
- (docker compose down)

### To Delete database volume:
- (docker compose down -v)

## To Run the App without Docker
If you prefer to run the app locally without needing Docker for the Spring Boot server:
1. Start only database with Docker:
```BASH
docker run --name codeanalysis-db -e POSTGRES_PASSWORD=password -e
POSTGRES_DB=codeanalysis -p 5432:5432 -d postgres:16
```
2. Create a `.env` file in project root:
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/codeanalysis
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=password
(Can be changed to whatever you prefer for username and password)

3. Run the Spring Boot server:
```BASH
mvnw.cmd spring-boot:run
```

## API Endpoints
| Method | Endpoint | Description
| POST | `/api/analysis/scan` | Submit code for analysis
| GET | `/api/analysis/history` | Retrieve all past scans
| GET | `/api/analysis/history/{fileName}` | Retrieve scans by filename
| GET | `/api/analysis/status | Status on the web service

### Example
**POST** `/api/analysis/scan`
```JSON
{
  "fileName": "OrderProcessor.java",
  "code": "import java.util.List;\n\npublic class OrderProcessor {\n    public void processOrder() {\n        System.out.println(\"processing\");\n    }\n}"
}
```

### Response
```JSON
{
  "fileName": "OrderProcessor.java",
  "methodCount": 1,
  "lineCount": 7,
  "importCount": 1,
  "complexityLevel": "LOW",
  "cyclomaticComplexity": 1,
  "dupMethods": [],
  "unusedImports": ["List"],
  "longestMethod": "processOrder",
  "longestMethodLines": 3,
  "recommendation": "Unused imports were found: List"
}
```

## Architecture
Browser (index.html) -> HTTP fetch (Analysis Controller @RestController) -> Analysis Service (@Service) -> Code Analyzer ScanRecordRepo (Analysis Engine & Spring Data JPA -> PostgreSQL (Docker)

## How the Code Analysis Works
The static analysis engine (`CodeAnalyzer.java`) processes the submitted code in several passes:
1. **Method Count Detection** - regex pattern matching helps to identify method signatures by scanning for return types, access modifier, and parameter lists
2. **Complexity Score** - cyclomatic complexity is calculated by the following keywords (`if`, `for`, `while`, `case`, `&&`, `||`, `?`)
3. **Duplicate Methods Detection** - method names are tracked within a list and flagged for when appears/seen more than once
4. **Import Analysis** - import statements are taken and cross-referenced with the code body to find unused ones
5. **Recommendation** - certain requirements are needed from complexity, method count, unused import count, line count, andduplicates to trigger specific suggestions for the code

**INSPIRED ON CURRENT (2026) STATIC CODE ANALYSIS TOOLS USED IN PROFESSIONAL SETTINGS LIKE SONARQUBE AND PMD
