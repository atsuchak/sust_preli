# Gemini Assistant Work Log: QueueStorm Investigator

This document tracks all the architecture scaffolding, code generation, and configuration accomplished by the Gemini Assistant during the setup phase of the `QueueStorm Investigator` hackathon project.

## 1. Requirements Analysis
* Analyzed `SUST_Hackathon_Preli_Problem_Statement.pdf` to extract API constraints, JSON schemas, required endpoints (`/health` and `/analyze-ticket`), enum values, safety rules, and secret handling requirements.

## 2. Spring Boot Initialization
* Generated a Spring Boot 3.x project using Maven and Java 17.
* Added necessary dependencies: `spring-boot-starter-web` (REST APIs), `spring-boot-starter-validation` (API contract validation), `spring-boot-starter-webflux` (WebClient for LLM API calls), and `lombok`.
* Registered a `WebClient.Builder` bean in `QueueStormInvestigatorApplication` to ensure successful application startup.

## 3. Data Transfer Objects (DTOs) & Enums
* Created strict Enums to prevent schema violations during automated judging:
  * `CaseType`
  * `Department`
  * `EvidenceVerdict`
  * `Severity`
* Created models mapped exactly to the required JSON schemas:
  * `AnalyzeTicketRequest` (with `@NotBlank` validation annotations)
  * `AnalyzeTicketResponse`
  * `TransactionHistoryEntry`

## 4. Controllers & Global Exception Handling
* **`HealthController`**: Exposes `GET /health` returning `{"status": "ok"}` as required by the judge harness.
* **`TicketAnalysisController`**: Exposes `POST /analyze-ticket` and validates incoming JSON payloads.
* **`GlobalExceptionHandler`**: Secures the application by:
  * Catching `HttpMessageNotReadableException` (returns 400 Bad Request).
  * Catching `MethodArgumentNotValidException` (returns 422 Unprocessable Entity).
  * Catching generic `Exception` (returns 500 Internal Server Error) to strictly prevent leaking stack traces or internal secrets to the user.

## 5. Service Layer Architecture
* Created `TicketAnalysisService` and its implementation `TicketAnalysisServiceImpl`.
* Created `LlmService` and its implementation `GeminiLlmServiceImpl` configured to interact with `https://generativelanguage.googleapis.com` using Spring's reactive `WebClient`. (Note: The actual LLM prompt integration is stubbed out for future development).

## 6. Secret Handling & Deployment Configuration
* **Environment Variables**: 
  * Created a `.env` file containing the real `GEMINI_API_KEY`.
  * Updated `.gitignore` to explicitly ignore `.env` from Git tracking.
  * Created an `.env.example` file with a placeholder as recommended in the rubric.
* **App Configuration**: Replaced `application.properties` with `application.yml`, configuring it to pull `GEMINI_API_KEY` from the environment.
* **Docker**: Created a `Dockerfile` using `eclipse-temurin:17-jre-alpine` to maintain an image size well under the 5GB limit.
* **Documentation**: Overwrote the root `README.md` to include setup instructions, tech stack details, AI strategy, models used, and known limitations.

## 7. Version Control Integration
* Staged, committed, and pushed all files to the remote GitHub repository in 6 logical stages:
  1. `chore: initialize spring boot base project`
  2. `docs: add preliminary problem statement pdf`
  3. `feat: add enums for ticket analysis taxonomy`
  4. `feat: add request and response dtos for analyze-ticket api`
  5. `feat: implement controllers, service stubs, and global exception handler`
  6. `chore: add docker deployment config, env example, and update readme`
