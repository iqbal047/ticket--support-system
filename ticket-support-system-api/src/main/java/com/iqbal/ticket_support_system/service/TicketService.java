package com.iqbal.ticket_support_system.service;

import com.iqbal.ticket_support_system.dto.AssignAgentResponse;
import com.iqbal.ticket_support_system.dto.TicketDTO;
import com.iqbal.ticket_support_system.model.Agent;
import com.iqbal.ticket_support_system.model.Ticket;
import com.iqbal.ticket_support_system.repository.AgentRepository;
import com.iqbal.ticket_support_system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AgentRepository agentRepository;

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketDTO.getTitle());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setPriority(ticketDTO.getPriority());
        ticket = ticketRepository.save(ticket);
        return convertToDTO(ticket);
    }

    public TicketDTO getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return convertToDTO(ticket);
    }

    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setTitle(ticketDTO.getTitle());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setStatus(ticketDTO.getStatus());
        ticket.setPriority(ticketDTO.getPriority());
        ticket = ticketRepository.save(ticket);
        return convertToDTO(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public AssignAgentResponse assignAgent(Long ticketId, Long agentId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));
        ticket.setAssignedAgent(agent);
        ticketRepository.save(ticket);

        AssignAgentResponse response = new AssignAgentResponse();
        response.setMessage("Ticket assigned successfully");
        response.setTicketId(ticketId);
        response.setAgentId(agentId);
        return response;
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());
        dto.setPriority(ticket.getPriority());
        dto.setCreatedDate(ticket.getCreatedDate());
        dto.setAssignedAgentId(ticket.getAssignedAgent() != null ? ticket.getAssignedAgent().getId() : null);
        return dto;
    }
}
