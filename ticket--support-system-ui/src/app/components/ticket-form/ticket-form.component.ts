
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TicketService } from '../../services/ticket.service';
import { Ticket, TicketStatus, TicketPriority } from '../../models/ticket.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-ticket-form',
  templateUrl: './ticket-form.component.html',
  styleUrls: ['./ticket-form.component.scss']
})
export class TicketFormComponent implements OnInit {
  ticketForm: FormGroup;
  ticketId: number | null = null;
  statuses = Object.values(TicketStatus);
  priorities = Object.values(TicketPriority);

  constructor(
    private fb: FormBuilder,
    private ticketService: TicketService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.ticketForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      status: [TicketStatus.OPEN],
      priority: [TicketPriority.MEDIUM, Validators.required]
    });
  }

  ngOnInit(): void {
    this.ticketId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.ticketId) {
      this.ticketService.getTicket(this.ticketId).subscribe({
        next: (ticket) => this.ticketForm.patchValue(ticket),
        error: () => this.snackBar.open('Error loading ticket', 'Close', { duration: 3000 })
      });
    }
  }

  save(): void {
    const ticket: Ticket = this.ticketForm.value;
    const request = this.ticketId
      ? this.ticketService.updateTicket(this.ticketId, ticket)
      : this.ticketService.createTicket(ticket);

    request.subscribe({
      next: () => {
        this.snackBar.open(this.ticketId ? 'Ticket updated' : 'Ticket created', 'Close', { duration: 3000 });
        this.router.navigate(['/tickets']);
      },
      error: () => this.snackBar.open('Error saving ticket', 'Close', { duration: 3000 })
    });
  }
}