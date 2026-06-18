import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Load } from '../../models/load.model';

@Injectable({ providedIn: 'root' })
export class LoadService {

  private base = 'http://localhost:8080/rpc/api';

  constructor(private http: HttpClient) {}

  getLoads(): Observable<Load[]> {
    return this.http.get<Load[]>(`${this.base}/loads`, { withCredentials: true });
  }

  addLoad(load: any): Observable<any> {
    return this.http.post(`${this.base}/loads/add`, load, { withCredentials: true });
  }
}