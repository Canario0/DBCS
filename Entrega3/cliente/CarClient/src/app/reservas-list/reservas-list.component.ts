import { RESERVAS } from './../shared/Reserva-mock';
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
  reservas: Reserva[] = RESERVAS;
  displayedCols = ['id', 'fechaReserva', 'fechaInicioAlquiler', 'fechaFinAlquiler', 'ejecutada', 'nif', 'matricula', 'actions'];

  constructor(private session: SessionService, private route: Router) { }

  ngOnInit() {
    // if (this.session.checkLoggedIn() === false) {
    //   this.route.navigate(['/login']);
    // }
  }

  editAction(id: number){
    console.log(`Me han editado ${id}`);
  }

  deleteAction(id: number){
    console.log(`Me han borrado ${id}`);
  }
}
