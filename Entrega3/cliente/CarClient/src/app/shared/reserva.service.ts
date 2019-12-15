import { DeleteMessage } from './delete-message';
import { Reserva } from './reserva';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {
  private static readonly baseURL = 'http://localhost:8080/DeployWebApplication/webresources/';
  constructor(private http: HttpClient) { }

  getReserva(nif: string): Observable<Reserva[]> {
    const url = ReservaService.baseURL + `user/${nif}/reserva`;
    return this.http.get<Reserva[]>(url);
  }

  deleteReserva(nif: string, id: number): Observable<DeleteMessage> {
    const url = ReservaService.baseURL + `user/${nif}/reserva/${id}`;
    return this.http.delete<DeleteMessage>(url);
  }
}
