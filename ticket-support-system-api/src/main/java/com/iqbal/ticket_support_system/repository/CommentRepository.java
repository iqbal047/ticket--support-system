package com.iqbal.ticket_support_system.repository;

import com.iqbal.ticket_support_system.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTicketId(Long ticketId);
}
