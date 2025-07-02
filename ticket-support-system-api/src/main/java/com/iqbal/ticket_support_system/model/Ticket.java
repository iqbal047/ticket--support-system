package com.iqbal.ticket_support_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    private TicketPriority priority;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "assigned_agent_id")
    private Agent assignedAgent;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        status = TicketStatus.OPEN;
    }
}
