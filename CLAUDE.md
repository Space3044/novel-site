# CLAUDE.md

## Response Rules
1. All response content (including technical explanations, code comments, step-by-step instructions, problem solutions, architecture analysis, etc.) must be in **Simplified Chinese**; English responses are strictly prohibited (except for code keywords and original technical terminology).
2. Technical terms must be accurate; where necessary, annotate the original English term in parentheses after the Chinese translation.
3. Code comments must use Simplified Chinese mandatorily.
4. Explanatory content should be easy to understand, avoiding pure English technical descriptions.

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot web application for an online novel hosting site called "novel-site". It uses SQLite as the database, MyBatis for data access, and Thymeleaf for the frontend templating engine.

## Architecture

### Technology Stack
- Java 11
- Spring Boot 2.7.18
- MyBatis for database access
- SQLite as the database (novel.db)
- Thymeleaf for server-side templating
- Lombok for reducing boilerplate code
- Maven for build management

### Project Structure
The application follows a standard Spring Boot MVC architecture with the following layers:

- **Controller Layer**: Located in `src/main/java/org/example/novelsite/controller/`
  - Handles HTTP requests and responses
  - Uses `@Controller` annotation for web pages and `@RestController` for APIs
  - Examples: `IndexController`, `NovelController`, `ReaderController`

- **Service Layer**: Located in `src/main/java/org/example/novelsite/service/`
  - Business logic implementation
  - Interfaces in `service/` and implementations in `service/impl/`
  - Dependency injection using constructor injection

- **Data Access Layer**: Located in `src/main/java/org/example/novelsite/mapper/`
  - MyBatis mapper interfaces
  - SQL implementations in `src/main/resources/mapper/*.xml`
  - Uses `@Mapper` annotation

- **Entity Layer**: Located in `src/main/java/org/example/novelsite/entity/`
  - JPA entities representing database tables
  - Uses Lombok annotations to reduce boilerplate

### Database Schema
The database contains tables for:
- readers: User accounts for readers
- authors: User accounts for authors
- novels: Novel information with metadata
- chapters: Individual novel chapters
- categories: Novel categories (玄幻, 武侠, 都市, etc.)
- comments: User comments on novels
- bookmarks: User bookmarks for novels
- reading_history: Track user reading progress
- messages: User messages to authors

## Development Commands

### Building and Running
- `./mvnw clean compile`: Compile the project
- `./mvnw spring-boot:run`: Run the application locally (server starts on port 8080)
- `./mvnw clean package`: Package the application as a JAR file
- `java -jar target/novel-site-0.0.1-SNAPSHOT.jar`: Run the packaged application

### Testing
- `./mvnw test`: Run all tests
- `./mvnw test -Dtest=TestClassname`: Run specific test class
- `./mvnw clean verify`: Run tests and build lifecycle

### Database
- The application automatically creates and initializes the database on startup via `DatabaseConfig.java`
- Schema is defined in `src/main/resources/schema.sql`
- Database file: `novel.db` (SQLite)

## Key Features

- User registration and authentication for readers and authors
- Novel publishing and management system
- Chapter-by-chapter reading experience
- User bookmarking and reading history
- Novel categorization and search
- Comment and rating system
- Administrative functions