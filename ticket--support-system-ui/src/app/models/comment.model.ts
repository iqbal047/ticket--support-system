// src/models/comment.model.ts
export interface Comment {
  id?: number;
  ticketId?: number;
  author: string;
  message: string;
  createdDate?: string;
}