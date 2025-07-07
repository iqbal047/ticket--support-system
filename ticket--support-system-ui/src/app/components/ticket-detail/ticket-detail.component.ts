// src/app/components/ticket-detail/ticket-detail.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TicketService } from '../../services/ticket.service';
import { AgentService } from '../../services/agent.service';
import { CommentService } from '../../services/comment.service';
//import { Ticket, TicketStatus, TicketPriority } from '../../models/ticket.model';
import { Agent } from '../../models/agent.model';
import { Comment } from '../../models/comment.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Ticket } from 'src/app/models/ticket.model';

@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.scss']
})
export class TicketDetailComponent implements OnInit {
  ticket: Ticket | null = null;
  agents: Agent[] = [];
  comments: Comment[] = [];

  constructor(
    private route: ActivatedRoute,
    private ticketService: TicketService,
    private agentService: AgentService,
    private commentService: CommentService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadTicket(id);
    this.loadAgents();
    this.loadComments(id);
  }

  loadTicket(id: number): void {
    this.ticketService.getTicket(id).subscribe({
      next: (ticket) => this.ticket = ticket,
      error: () => this.snackBar.open('Error loading ticket', 'Close', { duration: 3000 })
    });
  }

  loadAgents(): void {
    this.agentService.getAllAgents().subscribe({
      next: (agents) => this.agents = agents,
      error: () => this.snackBar.open('Error loading agents', 'Close', { duration: 3000 })
    });
  }

  loadComments(ticketId: number): void {
    this.commentService.getCommentsByTicketId(ticketId).subscribe({
      next: (comments) => this.comments = comments,
      error: () => this.snackBar.open('Error loading comments', 'Close', { duration: 3000 })
    });
  }

  assignAgent(agentId: number): void {
    if (this.ticket?.id) {
      this.ticketService.assignAgent(this.ticket.id, agentId).subscribe({
        next: () => {
          this.snackBar.open('Agent assigned successfully', 'Close', { duration: 3000 });
          this.loadTicket(this.ticket!.id!);
        },
        error: () => this.snackBar.open('Error assigning agent', 'Close', { duration: 3000 })
      });
    }
  }
}