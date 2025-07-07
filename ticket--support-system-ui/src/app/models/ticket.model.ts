// src/app/models/ticket.model.ts
export enum TicketStatus {
  OPEN = 'OPEN',
  IN_PROGRESS = 'IN_PROGRESS',
  RESOLVED = 'RESOLVED',
  CLOSED = 'CLOSED'
}

export enum TicketPriority {
  LOW = 'LOW',
  MEDIUM = 'MEDIUM',
  HIGH = 'HIGH'
}

export interface Ticket {
  id?: number;
  title: string;
  description: string;
  status?: TicketStatus;
  priority: TicketPriority;
  createdDate?: string;
  assignedAgentId?: number | null;
}