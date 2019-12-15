import { LoginMessage } from './login-message';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private isLogged: boolean;
  private nif: string;
  constructor() {
    this.isLogged = false;
  }

  setLoggedIn(nif: string) {
    this.nif = nif;
    this.isLogged = true;
  }

  getNif() {
    return this.nif;
  }

  checkLoggedIn() {
    return this.isLogged;
  }

  logout() {
    this.isLogged = false;
    this.nif = '';
  }
}
