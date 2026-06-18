import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { AuthService } from '../../core/services/auth';
import { ProfitService } from '../../core/services/profit';
import { ProfitHistory } from '../../models/profit-history.model';

@Component({
  selector: 'app-history',
  imports: [RouterLink, DatePipe],
  templateUrl: './history.html',
  styleUrl: './history.css'
})
export class History implements OnInit {
  user: any;
  history: ProfitHistory[] = [];
  loading = true;
  error   = '';

  constructor(
    private auth: AuthService,
    private profitService: ProfitService,
    private router: Router
  ) {}

  ngOnInit() {
    this.user = this.auth.getUser();
    this.profitService.getHistory().subscribe({
      next: (data) => {
        this.history = data;
        this.loading = false;
      },
      error: () => {
        this.error   = 'Failed to load history.';
        this.loading = false;
      }
    });
  }

  logout() {
    this.auth.logout().subscribe({
      next: () => { this.auth.clearUser(); this.router.navigate(['/login']); },
      error: () => { this.auth.clearUser(); this.router.navigate(['/login']); }
    });
  }
}