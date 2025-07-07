import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Agent } from 'src/app/models/agent.model';
import { Ticket } from 'src/app/models/ticket.model';
import { AgentService } from 'src/app/services/agent.service';
import { TicketService } from 'src/app/services/ticket.service';

@Component({
  selector: 'app-agent-detail',
  templateUrl: './agent-detail.component.html',
  styleUrls: ['./agent-detail.component.scss']
})
export class AgentDetailComponent implements OnInit {

  agent: Agent | null = null; 
  tickets: Ticket[] = [];

  constructor(
    private route: ActivatedRoute,
    private agentService: AgentService,
    private ticketService: TicketService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadAgent(id);
    this.loadTickets(id);
  }

  loadAgent(id: number): void {
    this.agentService.getAgent(id).subscribe({
      next: (agent) => this.agent = agent,
      error: () => this.snackBar.open('Error loading agent', 'Close', { duration: 3000 })
    });
  }

  loadTickets(agentId: number): void {
    this.ticketService.getAllTickets().subscribe({
      next: (tickets) => this.tickets = tickets.filter(ticket => ticket.assignedAgentId === agentId),
      error: () => this.snackBar.open('Error loading tickets', 'Close', { duration: 3000 })
    });
  }

 }

// src/app/components/agent-detail/agent-detail.component.ts
// import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute } from '@angular/router';
// import { AgentService } from '../../services/agent.service';
// import { Agent } from '../../models/agent.model';
// import { MatSnackBar } from '@angular/material/snack-bar';
// import { TicketService } from '../../services/ticket.service';
// import { Ticket } from '../../models/ticket.model';

// @Component({
//   selector: 'app-agent-detail',
//   templateUrl: './agent-detail.component.html',
//   styleUrls: ['./agent-detail.component.scss']
// })
// export class AgentDetailComponent implements OnInit {

// }
