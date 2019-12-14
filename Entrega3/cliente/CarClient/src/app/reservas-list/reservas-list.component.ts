import { Router } from '@angular/router';
import { SessionService } from './../shared/session.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reservas-list',
  templateUrl: './reservas-list.component.html',
  styleUrls: ['./reservas-list.component.css']
})
export class ReservasListComponent implements OnInit {

  constructor(private session: SessionService, private route: Router) { }

  ngOnInit() {
    if (this.session.checkLoggedIn() === false) {
      this.route.navigate(['/login']);
    }
  }

}
