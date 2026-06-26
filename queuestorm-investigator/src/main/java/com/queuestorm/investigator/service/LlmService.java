package com.queuestorm.investigator.service;

import com.queuestorm.investigator.dto.AnalyzeTicketRequest;
import com.queuestorm.investigator.dto.AnalyzeTicketResponse;

public interface LlmService {
    AnalyzeTicketResponse analyzeTicket(AnalyzeTicketRequest request);
}
