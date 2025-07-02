package com.iqbal.ticket_support_system.repository;

import com.iqbal.ticket_support_system.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
