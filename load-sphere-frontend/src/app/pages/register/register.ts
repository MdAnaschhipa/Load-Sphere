import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  name     = '';
  email    = '';
  phone    = '';
  password = '';
  role     = 'owner';
  error    = '';
  success  = '';
  loading  = false;

  constructor(private auth: AuthService, private router: Router) {}

  onSubmit() {
    this.error   = '';
    this.success = '';
    this.loading = true;

    this.auth.register({
      name:     this.name,
      email:    this.email,
      phone:    this.phone,
      password: this.password,
      role:     this.role
    }).subscribe({
      next: () => {
        this.success = 'Account created! Redirecting to login...';
        setTimeout(() => this.router.navigate(['/login']), 1500);
      },
      error: (err: any) => {
        this.error   = err.error?.message || 'Registration failed. Please try again.';
        this.loading = false;
      }
    });
  }
}