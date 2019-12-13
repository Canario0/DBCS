import { MatSnackBar } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { LoginForm } from '../shared/login-form';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login: LoginForm = { user: '', password: '' };
  // errorMessage = "";

  constructor(private snackbar: MatSnackBar) { }

  ngOnInit() {
  }

  onSubmit() {
    console.log('Bro sos mafioso' + this.login.user);
    this.snackbar.open('Usuario inválido', 'close', { duration: 2000, verticalPosition: 'top' });
  }

}
