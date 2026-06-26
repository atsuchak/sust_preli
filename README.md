# QueueStorm Investigator

An AI / API SupportOps Service for Digital Finance (SUST CSE Carnival 2026 Codex Community Hackathon).

## Tech Stack
* **Java 17** & **Spring Boot 3.3**
* **Spring WebFlux** for making asynchronous calls to the LLM API.
* **Lombok** for boilerplate reduction.
* **Jakarta Validation** for API contract enforcement.

## Setup Instructions
1. Ensure Java 17 and Maven are installed.
2. Provide your Gemini API key in `queuestorm-investigator/src/main/resources/application.yml` or export it as an environment variable `GEMINI_API_KEY`.
3. Navigate to the project directory: `cd queuestorm-investigator`
4. Build the project: `./mvnw clean package`

## Run Command
**Local Development:**
```bash
./mvnw spring-boot:run
```
Or via Docker:
```bash
docker build -t queuestorm-investigator .
docker run -p 8080:8080 -e GEMINI_API_KEY=your_key queuestorm-investigator
```

## Architecture and AI Approach
This application delegates the ticket analysis process to Google's Gemini models using the Spring WebFlux `WebClient`. 

### Safety Logic
The system applies strict guardrails to prevent credentials scraping (never ask for PIN, OTP, etc.) and avoids authorizing refunds on its own. The fallback logic will escalate the cases by setting `human_review_required: true`. 
The `GlobalExceptionHandler` ensures that stack traces and other application internals are never exposed to the client in the event of an error.

### MODELS
* **Model Used:** Gemini 1.5 Pro (or Gemini 1.5 Flash depending on latency requirements)
* **Where it runs:** Google AI Studio (external API)
* **Why it was chosen:** Chosen for excellent reasoning capabilities on long text (like combining complaints with a history of transactions) and strong JSON-mode outputs to map precisely to our API response schema.

## Assumptions and Known Limitations
* Assumes the Gemini API endpoint responds within the 30-second enforceable timeout limit.
* The API does not yet implement retry mechanisms for rate limits from Google AI Studio.
