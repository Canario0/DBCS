import { MatSnackBar } from '@angular/material';
import { ReservaService } from './../shared/reserva.service';
import { Reserva } from './../shared/reserva';
import { Router } from '@angular/router';
import { SessionService } from './../shared/session.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reservas-list',
  templateUrl: './reservas-list.component.html',
  styleUrls: ['./reservas-list.component.css']
})
export class ReservasListComponent implements OnInit {
  reservas: Reserva[];
  displayedCols = ['id', 'fechaReserva', 'fechaInicioAlquiler', 'fechaFinAlquiler', 'ejecutada', 'nif', 'matricula', 'actions'];

  constructor(
    private reservaService: ReservaService,
    private snackbar: MatSnackBar,
    private session: SessionService,
    private route: Router
  ) { }

  ngOnInit() {
    if (!this.session.checkLoggedIn()) {
      this.route.navigate(['/login']);
    }
    this.getReservas();
  }

  editAction(id: number) {
    this.route.navigate(['/reserva', id]);
  }

  deleteAction(id: number) {
    this.reservaService.deleteReserva(this.session.getNif(), id).subscribe(
      resp => {
        this.getReservas();
        this.snackbar.open(resp.message, 'close', { duration: 2000, verticalPosition: 'top' });
      },
      err => {
        if (err.status === 403) {
          this.snackbar.open(err.error.message, 'close', { duration: 2000, verticalPosition: 'top' });
        } else {
          console.log('Error al borrar reserva: ' + err.message);
          throw err;
        }
      }
    );
  }

  getReservas() {
    this.reservaService.getReservas(this.session.getNif()).subscribe(
      resp => {
        this.reservas = resp;
      },
      err => {
        if (err.status === 404) {
          this.snackbar.open(err.error.message, 'close', { duration: 2000, verticalPosition: 'top' });
        } else {
          console.log('Error al obtener las reservas: ' + err.message);
          throw err;
        }
      }
    );
  }

  newAction() {
    console.log('Añadir una nueva reserva');
  }
}
