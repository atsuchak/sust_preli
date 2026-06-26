package com.queuestorm.investigator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class AnalyzeTicketRequest {
    @NotBlank
    @JsonProperty("ticket_id")
    private String ticketId;

    @NotBlank
    private String complaint;

    private String language;
    private String channel;
    
    @JsonProperty("user_type")
    private String userType;

    @JsonProperty("campaign_context")
    private String campaignContext;

    @JsonProperty("transaction_history")
    private List<TransactionHistoryEntry> transactionHistory;

    private Map<String, Object> metadata;
}
