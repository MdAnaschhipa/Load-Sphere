import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth';
import { LoadService } from '../../core/services/load';

@Component({
  selector: 'app-add-load',
  imports: [FormsModule, RouterLink],
  templateUrl: './add-load.html',
  styleUrl: './add-load.css'
})
export class AddLoad {
  origin      = '';
  destination = '';
  cargoType   = '';
  weightTons  = 0;
  distanceKm  = 0;
  quotedRate  = 0;

  loading = false;
  success = '';
  error   = '';

  constructor(
    private auth: AuthService,
    private loadService: LoadService,
    private router: Router
  ) {}

  onSubmit() {
    this.error   = '';
    this.success = '';
    this.loading = true;

    const payload = {
      origin:      this.origin,
      destination: this.destination,
      cargoType:   this.cargoType,
      weightTons:  this.weightTons,
      distanceKm:  this.distanceKm,
      quotedRate:  this.quotedRate
    };

    this.loadService.addLoad(payload).subscribe({
      next: () => {
        this.success = 'Load posted successfully!';
        this.loading = false;
        this.reset();
      },
      error: (err: any) => {
  this.error   = err.error?.message || 'Failed to post load.';
  this.loading = false;
}
    });
  }

  reset() {
    this.origin      = '';
    this.destination = '';
    this.cargoType   = '';
    this.weightTons  = 0;
    this.distanceKm  = 0;
    this.quotedRate  = 0;
  }

  logout() {
    this.auth.logout().subscribe({
      next: () => { this.auth.clearUser(); this.router.navigate(['/login']); },
      error: () => { this.auth.clearUser(); this.router.navigate(['/login']); }
    });
  }
}