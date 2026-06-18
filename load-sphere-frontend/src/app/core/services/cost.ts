import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CostInput } from '../../models/cost-input.model';

@Injectable({ providedIn: 'root' })
export class CostService {

  private base = 'http://localhost:8080/rpc/api';

  constructor(private http: HttpClient) {}

  calculate(costData: any): Observable<any> {
    return this.http.post(`${this.base}/calculate`, costData, { withCredentials: true });
  }
}