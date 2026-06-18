import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  email    = '';
  password = '';
  error    = '';
  loading  = false;

  constructor(private auth: AuthService, private router: Router) {}

  onSubmit() {
  this.error   = '';
  this.loading = true;

  this.auth.login(this.email, this.password).subscribe({
    next: (res) => {
      this.auth.saveUser(res); // saveUser handles redirect based on role
    },
    error: (err: any) => {
      this.error   = err.error?.message || 'Login failed. Please try again.';
      this.loading = false;
    }
  });
}
}