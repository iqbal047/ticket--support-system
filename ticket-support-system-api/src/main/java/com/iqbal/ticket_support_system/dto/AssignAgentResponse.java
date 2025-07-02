package com.iqbal.ticket_support_system.dto;

import lombok.Data;

@Data
public class AssignAgentResponse {
    private String message;
    private Long ticketId;
    private Long agentId;
}
