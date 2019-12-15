import { MatSnackBar } from '@angular/material';
import { SessionService } from './../shared/session.service';
import { ReservaService } from './../shared/reserva.service';
import { Reserva } from './../shared/reserva';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-editar-reserva',
  templateUrl: './editar-reserva.component.html',
  styleUrls: ['./editar-reserva.component.css']
})
export class EditarReservaComponent implements OnInit {
  reserva: Reserva;

  constructor(
    private snackbar: MatSnackBar,
    private session: SessionService,
    private route: ActivatedRoute,
    private router: Router,
    private reservaService: ReservaService
  ) { }

  ngOnInit() {
    if (!this.session.checkLoggedIn()) {
      this.router.navigate(['/login']);
    }
    let id: string;
    this.route.paramMap.subscribe(
      params => {
        id = params.get('id');
      },
      err => console.log('Error al leer id para editar: ' + err)
    );
    this.reservaService.getReserva(this.session.getNif(), id).subscribe(
      resp => this.reserva = resp,
      err => {
        if (err.status === 404) {
          this.snackbar.open(err.error.message, 'close', { duration: 2000, verticalPosition: 'top' });
        } else {
          console.log('Error al obtener la reserva: ' + err.message);
          throw err;
        }
      }
    );
  }

  onSubmit() {
    this.reservaService.putReserva(this.session.getNif(), this.reserva).subscribe(
      resp => {
        this.snackbar.open(resp.message, 'close', { duration: 2000, verticalPosition: 'top' });
        this.router.navigate(['/reservas']);
      },
      err => {
        if (err.status === 403) {
          this.snackbar.open(err.error.message, 'close', { duration: 2000, verticalPosition: 'top' });
        } else {
          console.log('Error al actualizar la reserva: ' + err.message);
          throw err;
        }
      }
    );
  }

  cancelAction() {
    this.router.navigate(['/reservas']);
  }
}
