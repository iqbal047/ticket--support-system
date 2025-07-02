package com.iqbal.ticket_support_system.controller;

import com.iqbal.ticket_support_system.dto.AgentDTO;
import com.iqbal.ticket_support_system.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {
    @Autowired
    private AgentService agentService;

    @PostMapping
    public ResponseEntity<AgentDTO> createAgent(@RequestBody AgentDTO agentDTO) {
        return ResponseEntity.ok(agentService.createAgent(agentDTO));
    }

    @GetMapping
    public ResponseEntity<List<AgentDTO>> getAllAgents() {
        return ResponseEntity.ok(agentService.getAllAgents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgent(@PathVariable Long id) {
        return ResponseEntity.ok(agentService.getAgent(id));
    }
}
