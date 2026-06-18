import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth';
import { LoadService } from '../../core/services/load';
import { Load } from '../../models/load.model';

@Component({
  selector: 'app-load-list',
  imports: [RouterLink],
  templateUrl: './load-list.html',
  styleUrl: './load-list.css'
})
export class LoadList implements OnInit {
  user: any;
  loads: Load[] = [];
  loading = true;
  error   = '';

  constructor(
    private auth: AuthService,
    private loadService: LoadService,
    private router: Router
  ) {}

  ngOnInit() {
    this.user = this.auth.getUser();
    this.loadService.getLoads().subscribe({
      next: (data) => {
        this.loads   = data;
        this.loading = false;
      },
      error: () => {
        this.error   = 'Failed to load data. Please try again.';
        this.loading = false;
      }
    });
  }

  selectLoad(load: Load) {
    localStorage.setItem('selectedLoad', JSON.stringify(load));
    this.router.navigate(['/calculator']);
  }

  logout() {
    this.auth.logout().subscribe({
      next: () => { this.auth.clearUser(); this.router.navigate(['/login']); },
      error: () => { this.auth.clearUser(); this.router.navigate(['/login']); }
    });
  }
}