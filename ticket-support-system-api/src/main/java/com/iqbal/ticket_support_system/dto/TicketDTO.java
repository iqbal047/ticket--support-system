package com.iqbal.ticket_support_system.dto;

import com.iqbal.ticket_support_system.model.TicketPriority;
import com.iqbal.ticket_support_system.model.TicketStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDTO {
    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private LocalDateTime createdDate;
    private Long assignedAgentId;
}
