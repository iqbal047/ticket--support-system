package com.iqbal.ticket_support_system.controller;

import com.iqbal.ticket_support_system.dto.AssignAgentResponse;
import com.iqbal.ticket_support_system.dto.TicketDTO;
import com.iqbal.ticket_support_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        return ResponseEntity.ok(ticketService.createTicket(ticketDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        return ResponseEntity.ok(ticketService.updateTicket(id, ticketDTO));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
//        ticketService.deleteTicket(id);
//        return ResponseEntity.ok().build();
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, String>> deleteTicket(@PathVariable Long id) {
//        ticketService.deleteTicket(id);
//
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "Deleted successfully");
//
//        return ResponseEntity.ok(response);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok("Deleted successfully");
    }



    @PostMapping("/{id}/assign/{agentId}")
    public ResponseEntity<AssignAgentResponse> assignAgent(@PathVariable Long id, @PathVariable Long agentId) {
        return ResponseEntity.ok(ticketService.assignAgent(id, agentId));
    }
}
