package com.queuestorm.investigator.controller;

import com.queuestorm.investigator.dto.AnalyzeTicketRequest;
import com.queuestorm.investigator.dto.AnalyzeTicketResponse;
import com.queuestorm.investigator.service.TicketAnalysisService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketAnalysisController {

    private final TicketAnalysisService ticketAnalysisService;

    public TicketAnalysisController(TicketAnalysisService ticketAnalysisService) {
        this.ticketAnalysisService = ticketAnalysisService;
    }

    @PostMapping("/analyze-ticket")
    public ResponseEntity<AnalyzeTicketResponse> analyzeTicket(@Valid @RequestBody AnalyzeTicketRequest request) {
        AnalyzeTicketResponse response = ticketAnalysisService.analyze(request);
        return ResponseEntity.ok(response);
    }
}
