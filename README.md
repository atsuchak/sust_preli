# QueueStorm Investigator

An AI / API SupportOps Service for Digital Finance (SUST CSE Carnival 2026 Codex Community Hackathon).

**Organizer Access**: Organizer access is granted to GitHub Handle: `bipulhf`.

## Tech Stack
* **Framework**: Spring Boot 3.3 (Java 17)
* **Web**: Spring Web (REST APIs)
* **Client**: Spring WebFlux (WebClient for LLM API calls)
* **Data Binding & Validation**: Jackson Databind, Spring Boot Starter Validation
* **Containerization**: Docker (eclipse-temurin:17-jre-alpine)

## Setup Instructions
1. **Ensure Prerequisites**: Java 17, Maven, and optionally Docker are installed.
2. **Clone the repository**: `git clone https://github.com/atsuchak/sust_preli.git`
3. **Set up Environment Variables**: 
   - Navigate to the `queuestorm-investigator` directory.
   - Copy `.env.example` to `.env` (Note: `.env` is correctly added to `.gitignore` to prevent secret leaks).
   - Add your Gemini API key: `GEMINI_API_KEY=your_actual_key_here`
4. **Build the Application**:
   - `cd queuestorm-investigator`
   - Windows: `.\mvnw clean package`
   - Linux/Mac: `./mvnw clean package`

## Run Command
### Locally via Maven
```bash
cd queuestorm-investigator
.\mvnw spring-boot:run
```

### Via Docker
```bash
cd queuestorm-investigator
docker build -t queuestorm-investigator .
docker run -p 8080:8080 --env-file .env queuestorm-investigator
```

## AI Approach & Safety Logic
This application delegates the ticket analysis process to Google's Gemini models using the Spring WebFlux `WebClient`. The prompt is engineered to act as a strict "Complaint Investigator", processing customer complaints against transaction histories.

**Safety Logic Implemented**:
- **Prompt Guardrails**: The LLM is explicitly instructed to never ask for PIN, OTP, password, or full card numbers.
- **Refund/Reversal Policy**: The LLM is explicitly blocked from confirming refunds, reversals, account unblocks, or recovery without authority. It is instructed to use language like *"any eligible amount will be returned through official channels"*.
- **Phishing Protection**: The system prevents the LLM from redirecting the customer to suspicious third-party channels (directs only to official support channels).
- **Prompt Injection Defense**: Explicit rules tell the LLM that adversarial complaint text must not override system rules, nullifying embedded prompt injections.
- **Data Safety**: The global exception handler explicitly obfuscates internal server errors to prevent leaking stack traces and API keys.

## MODELS Section
* **Model Used**: `gemini-2.5-flash`
* **Where it runs**: Google API (`generativelanguage.googleapis.com`)
* **Why it was chosen**: 
  - Extremely fast generation speed, ensuring the service comfortably meets the < 30 seconds enforced response time limit.
  - High instruction-following capability, which strictly adheres to the complex JSON schema without deviating or injecting markdown.
  - Cost-effective while maintaining high accuracy in text classification and reasoning.

## Known Limitations & Assumptions
- **Context Window Limits**: Assumes typical 2 to 5 entries for transaction history as per the problem statement.
- **Latency Spikes**: Outbound HTTPS calls to Google APIs might occasionally spike in latency based on Google's load, though usually well within the 30s envelope.
- **Simulated Responses**: The service uses synthetic evaluation data (`sample_output.json`); it does not connect to real financial gateways or user databases. No real customer/payment data is hardcoded in this repository.
