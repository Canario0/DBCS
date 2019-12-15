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
    this.reservaService.getReserva(this.session.getNif()).subscribe(
      resp => {
        this.reservas = resp;
      },
      err => {
        if (err.status === 404) {
          this.snackbar.open(err.error.message, 'close', { duration: 2000, verticalPosition: 'top' });
        } else {
          console.log('Error al iniciar sesión: ' + err.message);
          throw err;
        }
      }
    );
  }

  editAction(id: number) {
    console.log(`Me han editado ${id}`);
  }

  deleteAction(id: number) {
    console.log(`Me han borrado ${id}`);
  }

  newAction() {
    console.log('Añadir una nueva reserva');
  }
}
