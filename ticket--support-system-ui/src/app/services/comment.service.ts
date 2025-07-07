// src/app/services/comment.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../models/comment.model';


@Injectable({
  providedIn: 'root'
})
export class CommentService {
  constructor(private http: HttpClient) {}

  createComment(ticketId: number, comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(`${environment.apiUrl}/tickets/${ticketId}/comments`, comment);
  }

  getCommentsByTicketId(ticketId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${environment.apiUrl}/tickets/${ticketId}/comments`);
  }
}