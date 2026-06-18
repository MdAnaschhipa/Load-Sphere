import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth';
import { ProfitService } from '../../core/services/profit';
import { ProfitHistory } from '../../models/profit-history.model';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {
  user: any;
  history: ProfitHistory[] = [];
  totalTrips    = 0;
  acceptedTrips = 0;
  rejectedTrips = 0;
  totalProfit   = 0;

  constructor(private auth: AuthService, private profit: ProfitService, private router: Router) {}

  ngOnInit() {
    this.user = this.auth.getUser();
    this.profit.getHistory().subscribe({
      next: (data) => {
        this.history       = data;
        this.totalTrips    = data.length;
        this.acceptedTrips = data.filter(h => h.decision === 'Accept').length;
        this.rejectedTrips = data.filter(h => h.decision === 'Reject').length;
        this.totalProfit   = data.reduce((sum, h) => sum + h.profitAmt, 0);
      },
      error: () => {}
    });
  }

  logout() {
    this.auth.logout().subscribe({
      next: () => {
        this.auth.clearUser();
        this.router.navigate(['/login']);
      },
      error: () => {
        this.auth.clearUser();
        this.router.navigate(['/login']);
      }
    });
  }
}