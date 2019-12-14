import { HttpErrorResponse } from '@angular/common/http';
import { LoginService } from './../shared/login.service';
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

  constructor(private snackbar: MatSnackBar, private loginService: LoginService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.loginService.getLogin(this.login.user, this.login.password).subscribe(
      resp => {
        // TODO: implementar redirección
        console.log('Redireccionando');
      },
      err => {
        if (err.status === 401) {
          this.snackbar.open(JSON.parse((err as HttpErrorResponse).error).message, 'close', { duration: 2000, verticalPosition: 'top' });
        } else {
          console.log('Error al iniciar sesión: ' + err.message);
          throw err;
        }
      }
    );
  }

}
