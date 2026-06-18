import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth';

export const authGuard: CanActivateFn = () => {
  const auth   = inject(AuthService);
  const router = inject(Router);

  if (auth.isLoggedIn()) {
    return true;
  }
  router.navigate(['/login']);
  return false;
};

// Inside src/app/core/guards/auth-guard.ts
export const transporterGuard: CanActivateFn = (route, state) => {
  const auth = inject(AuthService);
  const user = auth.getUser();
  
  // Checking lowercase to match your MySQL 'transporter' value
  if (user && user.role?.toLowerCase() === 'transporter') {
    return true;
  }
  
  // If not a transporter, send them back to login
  const router = inject(Router);
  router.navigate(['/login']);
  return false;
};

export const ownerGuard: CanActivateFn = () => {
  const auth   = inject(AuthService);
  const router = inject(Router);

  const user = auth.getUser();
  if (user && (user.role === 'owner' || user.role === 'admin')) {
    return true;
  }
  router.navigate(['/dashboard']);
  return false;
};