// src/app/services/agent.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Agent } from '../models/agent.model';


@Injectable({
  providedIn: 'root'
})
export class AgentService {
  private apiUrl = `${environment.apiUrl}/agents`;

  constructor(private http: HttpClient) {}

  createAgent(agent: Agent): Observable<Agent> {
    return this.http.post<Agent>(this.apiUrl, agent);
  }

  getAllAgents(): Observable<Agent[]> {
    return this.http.get<Agent[]>(this.apiUrl);
  }

  getAgent(id: number): Observable<Agent> {
    return this.http.get<Agent>(`${this.apiUrl}/${id}`);
  }
}