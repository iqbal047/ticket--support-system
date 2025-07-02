package com.iqbal.ticket_support_system.controller;

import com.iqbal.ticket_support_system.dto.CommentDTO;
import com.iqbal.ticket_support_system.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets/{ticketId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long ticketId, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(ticketId, commentDTO));
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long ticketId) {
        return ResponseEntity.ok(commentService.getCommentsByTicketId(ticketId));
    }
}
