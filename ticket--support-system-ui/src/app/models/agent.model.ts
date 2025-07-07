// src/app/models/agent.model.ts
export interface Agent {
  id?: number;
  name: string;
  email: string;
  specialty?: string;
  assignedTicketIds?: number[];
}