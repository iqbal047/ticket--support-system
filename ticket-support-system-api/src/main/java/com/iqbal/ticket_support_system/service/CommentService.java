package com.iqbal.ticket_support_system.service;

import com.iqbal.ticket_support_system.dto.CommentDTO;
import com.iqbal.ticket_support_system.model.Comment;
import com.iqbal.ticket_support_system.model.Ticket;
import com.iqbal.ticket_support_system.repository.CommentRepository;
import com.iqbal.ticket_support_system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public CommentDTO createComment(Long ticketId, CommentDTO commentDTO) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Comment comment = new Comment();
        comment.setTicket(ticket);
        comment.setAuthor(commentDTO.getAuthor());
        comment.setMessage(commentDTO.getMessage());
        comment = commentRepository.save(comment);
        return convertToDTO(comment);
    }

    public List<CommentDTO> getCommentsByTicketId(Long ticketId) {
        return commentRepository.findByTicketId(ticketId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setTicketId(comment.getTicket().getId());
        dto.setAuthor(comment.getAuthor());
        dto.setMessage(comment.getMessage());
        dto.setCreatedDate(comment.getCreatedDate());
        return dto;
    }
}
