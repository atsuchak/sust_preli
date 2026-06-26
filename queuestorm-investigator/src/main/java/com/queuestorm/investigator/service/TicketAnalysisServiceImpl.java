package com.queuestorm.investigator.service;

import com.queuestorm.investigator.dto.AnalyzeTicketRequest;
import com.queuestorm.investigator.dto.AnalyzeTicketResponse;
import org.springframework.stereotype.Service;

@Service
public class TicketAnalysisServiceImpl implements TicketAnalysisService {

    private final LlmService llmService;

    public TicketAnalysisServiceImpl(LlmService llmService) {
        this.llmService = llmService;
    }

    @Override
    public AnalyzeTicketResponse analyze(AnalyzeTicketRequest request) {
        // Delegate complex analysis to LLM service
        // This is a placeholder for the actual logic integrating with Gemini
        return llmService.analyzeTicket(request);
    }
}
