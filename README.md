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

**Containrization
- Docker, Docker Compose

**Build Tool**
- Maven

## ▶️ Getting Started
