import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { CarResponse } from './../shared/trims';
import { Observable } from 'rxjs';
import { ReservaService } from './../shared/reserva.service';
import { Vehiculo } from './../shared/vehiculo';
import { SessionService } from './../shared/session.service';
import { Reserva } from './../shared/reserva';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nuevo-coche',
  templateUrl: './nuevo-coche.component.html',
  styleUrls: ['./nuevo-coche.component.css']
})
export class NuevoCocheComponent implements OnInit {
  reserva: Reserva = {
    idreserva: 0,
    fechareserva: new Date(Date.now()),
    fechainicioalquiler: null,
    fechafinalquiler: null,
    ejecutada: 'F',
    nif: this.session.getNif(),
    matricula: ''
  };
  showInfo: boolean = false;
  vehiculos: Observable<Vehiculo[]>;
  constructor(private snackbar: MatSnackBar,private session: SessionService, private reservaService: ReservaService, private router: Router) { }
  modelos: CarResponse;
  currentMat: string = '';
  ngOnInit() {
    if (!this.session.checkLoggedIn()) {
      this.router.navigate(['/login']);
    }
  }

  info(vehiculo: Vehiculo) {
    this.showInfo = true;
    this.currentMat = vehiculo.matricula;
    this.reservaService.getCarInfo(vehiculo.modelo.fabricante, vehiculo.modelo.nombre, vehiculo.modelo.año).subscribe(
      resp => {
        this.modelos = resp;
        console.log(this.modelos);
        console.log(resp);
      }
    );
  }

  onDateFilled() {
    if (this.reserva.fechainicioalquiler !== null && this.reserva.fechafinalquiler !== null) {
      this.vehiculos = this.reservaService.getVehiculos(
        this.session.getNif(),
        this.reserva.fechainicioalquiler,
        this.reserva.fechafinalquiler);
    }
  }

  cancelAction() {
    this.router.navigate(['/reservas']);
  }

  onSubmit() {
    this.reservaService.postReserva(this.session.getNif(), this.reserva).subscribe(
      resp => {
        this.snackbar.open(resp.message, 'close', { duration: 2000, verticalPosition: 'top' });
        this.router.navigate(['/reservas']);
      },
      err => {
        if (err.status === 403) {
          this.snackbar.open(err.error.message, 'close', { duration: 2000, verticalPosition: 'top' });
        } else {
          console.log('Error al crear la reserva: ' + err.message);
          throw err;
        }
      }
    );
  }

}
