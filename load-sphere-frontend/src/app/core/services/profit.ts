import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProfitHistory } from '../../models/profit-history.model';

@Injectable({ providedIn: 'root' })
export class ProfitService {

  private base = 'http://localhost:8080/rpc/api';

  constructor(private http: HttpClient) {}

  saveHistory(data: any): Observable<any> {
    return this.http.post(`${this.base}/history`, data, { withCredentials: true });
  }

  getHistory(): Observable<ProfitHistory[]> {
    return this.http.get<ProfitHistory[]>(`${this.base}/history`, { withCredentials: true });
  }
}