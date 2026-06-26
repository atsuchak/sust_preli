package com.queuestorm.investigator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionHistoryEntry {
    @JsonProperty("transaction_id")
    private String transactionId;

    private Instant timestamp;
    private String type;
    private BigDecimal amount;
    private String counterparty;
    private String status;
}
