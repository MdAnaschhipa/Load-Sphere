import { Routes } from '@angular/router';
import { authGuard, transporterGuard, ownerGuard } from './core/guards/auth-guard';

export const routes: Routes = [
  { path: '',         redirectTo: 'login', pathMatch: 'full' },
  { path: 'login',    loadComponent: () => import('./pages/login/login').then(m => m.Login) },
  { path: 'register', loadComponent: () => import('./pages/register/register').then(m => m.Register) },
  {
    path: '',
    canActivate: [authGuard],
    children: [
      { path: 'dashboard',  loadComponent: () => import('./pages/dashboard/dashboard').then(m => m.Dashboard) },
      // Owner only
      { path: 'loads',      canActivate: [ownerGuard],       loadComponent: () => import('./pages/load-list/load-list').then(m => m.LoadList) },
      { path: 'calculator', canActivate: [ownerGuard],       loadComponent: () => import('./pages/calculator/calculator').then(m => m.Calculator) },
      { path: 'history',    canActivate: [ownerGuard],       loadComponent: () => import('./pages/history/history').then(m => m.History) },
      // Transporter only
      { path: 'add-load',   canActivate: [transporterGuard], loadComponent: () => import('./pages/add-load/add-load').then(m => m.AddLoad) },
    ]
  },
  { path: '**', redirectTo: 'login' }
];