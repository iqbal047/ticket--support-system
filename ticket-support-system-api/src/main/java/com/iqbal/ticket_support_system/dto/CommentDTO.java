package com.iqbal.ticket_support_system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private Long ticketId;
    private String author;
    private String message;
    private LocalDateTime createdDate;
}
