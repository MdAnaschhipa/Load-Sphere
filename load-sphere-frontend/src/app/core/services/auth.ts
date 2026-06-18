import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private base = 'http://localhost:8080/rpc/api';

  constructor(private http: HttpClient, private router: Router) {}

  // --- Authentication API Calls ---

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.base}/login`, { email, password }, { withCredentials: true });
  }

  register(data: any): Observable<any> {
    return this.http.post(`${this.base}/register`, data, { withCredentials: true });
  }

  logout(): Observable<any> {
    return this.http.post(`${this.base}/logout`, {}, { withCredentials: true });
  }

  // --- Local Session Management ---

  saveUser(user: any): void {
    localStorage.setItem('user', JSON.stringify(user));
    // After saving, we automatically move the user to the right page
    this.redirectBasedOnRole(user.role);
  }

  getUser(): any {
    const u = localStorage.getItem('user');
    return u ? JSON.parse(u) : null;
  }

  clearUser(): void {
    localStorage.removeItem('user');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('user');
  }

  // --- Role-Based Routing ---

  private redirectBasedOnRole(role: string): void {
  // Use .trim().toUpperCase() to handle the lowercase 'transporter' in your DB
  const normalizedRole = role?.trim().toUpperCase();
  
  console.log('User Role detected:', normalizedRole);

  switch (normalizedRole) {
    case 'ADMIN':
      this.router.navigate(['/admin-dashboard']);
      break;
    case 'TRANSPORTER':
      // This MUST match the 'path' in your app.routes.ts
      this.router.navigate(['/add-load']); 
      break;
    case 'OWNER':
    default:
      this.router.navigate(['/dashboard']);
      break;
  }
}
}