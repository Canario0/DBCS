import { LoginMessage } from './login-message';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private static readonly baseURL = 'http://localhost:8080/DeployWebApplication/webresources/login';

  constructor(private http: HttpClient) { }

  getLogin(user: string, password: string): Observable<LoginMessage> {
    const url = LoginService.baseURL + '/' + user;
    return this.http.get<LoginMessage>(url, {
      headers: new HttpHeaders({
        Authorization: password
      })
    });
  }
}
