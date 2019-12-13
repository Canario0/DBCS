import { Component, OnInit } from '@angular/core';
import { LoginForm } from '../shared/login-form';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login: LoginForm = { user: '', password: '' };
  errorMessage="";

  constructor() { }

  ngOnInit() {
  }

  onSubmit() {
    console.log('Bro sos mafioso' + this.login.user);
  }

}
