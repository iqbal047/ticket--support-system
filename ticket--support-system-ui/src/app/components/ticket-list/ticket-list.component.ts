import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TicketService } from 'src/app/services/ticket.service';
// import { Ticket } from 'src/models/ticket.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Ticket } from 'src/app/models/ticket.model';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.scss']
})
export class TicketListComponent implements OnInit {

     tickets: Ticket[] = [];
     displayedColumns: string[] = ['id', 'title', 'status', 'priority', 'createdDate', 'actions'];

  constructor(
    private ticketService: TicketService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets(): void {
    this.ticketService.getAllTickets().subscribe({
      next: (tickets) => this.tickets = tickets,
      error: () => this.snackBar.open('Error', 'Failed to load tickets', { duration: 3000 })
    });
  }

  deleteTicket(id: number): void {
    if (confirm('Are you sure you want to delete this ticket?')) {
      this.ticketService.deleteTicket(id).subscribe({
        next: () => {
          this.snackBar.open('Ticket deleted', 'Close', { duration: 3000 });
          this.loadTickets();
        },
        error: () => this.snackBar.open('Error deleting ticket', 'Close', { duration: 3000 })
      });
    }
  }

}








