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
        if (resp.status < 401) {
          this.snackbar.open(resp.body.message, 'close', { duration: 2000, verticalPosition: 'top' });
        } else {
          // TODO: implementar redirección
          console.log('Redireccionando');
        }
      },
      err => {
        console.log('Error al iniciar sesión: ' + err.message);
        throw err;
      }
    );
  }

}
