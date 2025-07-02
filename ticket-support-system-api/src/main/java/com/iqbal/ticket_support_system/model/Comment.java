package com.iqbal.ticket_support_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    private String author;
    private String message;
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }
}
