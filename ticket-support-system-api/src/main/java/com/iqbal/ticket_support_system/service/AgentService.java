package com.iqbal.ticket_support_system.service;

import com.iqbal.ticket_support_system.dto.AgentDTO;
import com.iqbal.ticket_support_system.model.Agent;
import com.iqbal.ticket_support_system.model.Ticket;
import com.iqbal.ticket_support_system.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentRepository;

    public AgentDTO createAgent(AgentDTO agentDTO) {
        Agent agent = new Agent();
        agent.setName(agentDTO.getName());
        agent.setEmail(agentDTO.getEmail());
        agent.setSpecialty(agentDTO.getSpecialty());
        agent.setAssignedTickets(Collections.emptyList()); // Initialize empty list
        agent = agentRepository.save(agent);
        return convertToDTO(agent);
    }

    public List<AgentDTO> getAllAgents() {
        return agentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AgentDTO getAgent(Long id) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found"));
        return convertToDTO(agent);
    }

    private AgentDTO convertToDTO(Agent agent) {
        AgentDTO dto = new AgentDTO();
        dto.setId(agent.getId());
        dto.setName(agent.getName());
        dto.setEmail(agent.getEmail());
        dto.setSpecialty(agent.getSpecialty());
        // Handle null assignedTickets
        List<Long> ticketIds = agent.getAssignedTickets() != null ?
                agent.getAssignedTickets().stream()
                        .map(Ticket::getId)
                        .collect(Collectors.toList()) :
                Collections.emptyList();
        dto.setAssignedTicketIds(ticketIds);
        return dto;
    }
}
