package com.queuestorm.investigator.service;

import com.queuestorm.investigator.dto.AnalyzeTicketRequest;
import com.queuestorm.investigator.dto.AnalyzeTicketResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiLlmServiceImpl implements LlmService {

    private final WebClient webClient;
    private final String apiKey;

    public GeminiLlmServiceImpl(WebClient.Builder webClientBuilder, @Value("${gemini.api.key:}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com").build();
        this.apiKey = apiKey;
    }

    @Override
    public AnalyzeTicketResponse analyzeTicket(AnalyzeTicketRequest request) {
        // TODO: Implement actual prompt construction and Gemini API call here
        // Handle parsing of Gemini's JSON response to AnalyzeTicketResponse
        
        return AnalyzeTicketResponse.builder()
                .ticketId(request.getTicketId())
                // .caseType(...) 
                // fill rest based on LLM response
                .build();
    }
}
