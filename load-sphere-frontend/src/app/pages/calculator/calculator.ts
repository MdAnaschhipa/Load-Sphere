import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth';
import { CostService } from '../../core/services/cost';
import { ProfitService } from '../../core/services/profit';
import { Load } from '../../models/load.model';

@Component({
  selector: 'app-calculator',
  imports: [FormsModule, RouterLink],
  templateUrl: './calculator.html',
  styleUrl: './calculator.css'
})
export class Calculator implements OnInit {
  user: any;
  selectedLoad: Load | null = null;

  // Inputs
  fuelRate        = 0;
  mileageKmpl     = 0;
  tollCharges     = 0;
  driverAllowance = 0;

  // Results
  fuelCost   = 0;
  totalCost  = 0;
  profit     = 0;
  decision   = '';
  calculated = false;
  saving     = false;
  saved      = false;
  error      = '';

  constructor(
    private auth: AuthService,
    private costService: CostService,
    private profitService: ProfitService,
    private router: Router
  ) {}

  ngOnInit() {
    this.user = this.auth.getUser();
    const stored = localStorage.getItem('selectedLoad');
    if (stored) {
      this.selectedLoad = JSON.parse(stored);
    }
  }

  calculate() {
    if (!this.selectedLoad) return;

    this.fuelCost  = (this.selectedLoad.distanceKm / this.mileageKmpl) * this.fuelRate;
    this.totalCost = this.fuelCost + this.tollCharges + this.driverAllowance;
    this.profit    = this.selectedLoad.quotedRate - this.totalCost;
    this.decision  = this.profit > 0 ? 'Accept' : 'Reject';
    this.calculated = true;
    this.saved      = false;
  }

  saveTrip() {
    if (!this.selectedLoad) return;
    this.saving = true;

    const payload = {
      loadId:    this.selectedLoad.loadId,
      totalCost: this.totalCost,
      profit:    this.profit,
      decision:  this.decision
    };

    this.profitService.saveHistory(payload).subscribe({
      next: () => {
        this.saving = false;
        this.saved  = true;
      },
      error: (err) => {
        this.error  = err.error?.message || 'Failed to save trip.';
        this.saving = false;
      }
    });
  }

  reset() {
    this.fuelRate        = 0;
    this.mileageKmpl     = 0;
    this.tollCharges     = 0;
    this.driverAllowance = 0;
    this.calculated      = false;
    this.saved           = false;
    this.error           = '';
  }

  logout() {
    this.auth.logout().subscribe({
      next: () => { this.auth.clearUser(); this.router.navigate(['/login']); },
      error: () => { this.auth.clearUser(); this.router.navigate(['/login']); }
    });
  }
}