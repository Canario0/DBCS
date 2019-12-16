import { CarResponse } from './trims';
import { ParamMap } from '@angular/router';
import { Vehiculo } from './vehiculo';
import { DeleteMessage } from './delete-message';
import { Reserva } from './reserva';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {
  private static readonly baseURL = 'http://localhost:8080/DeployWebApplication/webresources/';
  constructor(private http: HttpClient) { }

  getReservas(nif: string): Observable<Reserva[]> {
    const url = ReservaService.baseURL + `user/${nif}/reserva`;
    return this.http.get<Reserva[]>(url);
  }

  getReserva(nif: string, id: string): Observable<Reserva> {
    const url = ReservaService.baseURL + `user/${nif}/reserva/${id}`;
    return this.http.get<Reserva>(url);
  }

  putReserva(nif: string, reserva: Reserva): Observable<DeleteMessage> {
    const url = ReservaService.baseURL + `user/${nif}/reserva/${reserva.idreserva}`;
    return this.http.put<DeleteMessage>(url, reserva);
  }

  postReserva(nif: string, reserva: Reserva): Observable<DeleteMessage> {
    const url = ReservaService.baseURL + `user/${nif}/reserva/`;
    return this.http.post<DeleteMessage>(url, reserva);
  }

  deleteReserva(nif: string, id: number): Observable<DeleteMessage> {
    const url = ReservaService.baseURL + `user/${nif}/reserva/${id}`;
    return this.http.delete<DeleteMessage>(url);
  }

  getVehiculos(nif: string, inicio: Date, fin: Date): Observable<Vehiculo[]> {
    const url = ReservaService.baseURL + `user/${nif}/car`;
    let params: HttpParams = new HttpParams()
      .set('fechaInicio', inicio.toISOString().slice(0, 10))
      .set('fechaFin', fin.toISOString().slice(0, 10));
    return this.http.get<Vehiculo[]>(url, {
      params
    });
  }

  getCarInfo(marca: string, modelo: string, year: number): Observable<CarResponse> {
    const url = 'https://www.carqueryapi.com/api/0.3/';
    let params: HttpParams = new HttpParams()
      .set('cmd', 'getTrims')
      .set('make', marca)
      .set('model', modelo)
      .set('year', year.toString());
    return this.http.get<any>(url, { params });
  }
}
