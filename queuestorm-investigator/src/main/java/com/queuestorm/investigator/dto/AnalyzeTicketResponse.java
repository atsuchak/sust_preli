package com.queuestorm.investigator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.queuestorm.investigator.enums.CaseType;
import com.queuestorm.investigator.enums.Department;
import com.queuestorm.investigator.enums.EvidenceVerdict;
import com.queuestorm.investigator.enums.Severity;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class AnalyzeTicketResponse {
    @JsonProperty("ticket_id")
    private String ticketId;

    @JsonProperty("relevant_transaction_id")
    private String relevantTransactionId;

    @JsonProperty("evidence_verdict")
    private EvidenceVerdict evidenceVerdict;

    @JsonProperty("case_type")
    private CaseType caseType;

    private Severity severity;
    private Department department;

    @JsonProperty("agent_summary")
    private String agentSummary;

    @JsonProperty("recommended_next_action")
    private String recommendedNextAction;

    @JsonProperty("customer_reply")
    private String customerReply;

    @JsonProperty("human_review_required")
    private boolean humanReviewRequired;

    private Double confidence;

    @JsonProperty("reason_codes")
    private List<String> reasonCodes;
}
