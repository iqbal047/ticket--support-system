package com.iqbal.ticket_support_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class AgentDTO {
    private Long id;
    private String name;
    private String email;
    private String specialty;
    private List<Long> assignedTicketIds;
}
