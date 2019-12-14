import { SessionService } from './../shared/session.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { LoginService } from './../shared/login.service';
import { MatSnackBar } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { LoginForm } from '../shared/login-form';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login: LoginForm = { user: '', password: '' };
  // errorMessage = "";

  constructor(private snackbar: MatSnackBar, private loginService: LoginService, private route: Router, private session: SessionService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.loginService.getLogin(this.login.user, this.login.password).subscribe(
      resp => {
        this.session.setLoggedIn(resp.body);
        this.route.navigate(['/reservas']);
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
