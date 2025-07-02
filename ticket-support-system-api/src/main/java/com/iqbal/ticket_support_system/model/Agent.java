package com.iqbal.ticket_support_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String specialty;

//    @OneToMany(mappedBy = "assignedAgent")
//    private List<Ticket> assignedTickets;

    // Agent.java
    @OneToMany(mappedBy = "assignedAgent")
    private List<Ticket> assignedTickets;
}
