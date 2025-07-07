import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Agent } from 'src/app/models/agent.model';
import { AgentService } from 'src/app/services/agent.service';

@Component({
  selector: 'app-agent-list',
  templateUrl: './agent-list.component.html',
  styleUrls: ['./agent-list.component.scss']
})
export class AgentListComponent implements OnInit{
    agents: Agent[] = [];
  displayedColumns: string[] = ['id', 'name', 'email', 'specialty', 'actions'];
  router: any;

  constructor(
    private agentService: AgentService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadAgents();
  }

  loadAgents(): void {
    this.agentService.getAllAgents().subscribe({
      next: (agents) => this.agents = agents,
      error: () => this.snackBar.open('Error loading agents', 'Close', { duration: 3000 })
    });
  }

    createAgent(): void {
    // Navigate to create agent form
    this.router.navigate(['/agents/create']);
  }

}
