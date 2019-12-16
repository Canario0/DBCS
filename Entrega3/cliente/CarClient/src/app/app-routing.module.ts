import { NuevoCocheComponent } from './nuevo-coche/nuevo-coche.component';
import { EditarReservaComponent } from './editar-reserva/editar-reserva.component';
import { ReservasListComponent } from './reservas-list/reservas-list.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'reservas', component: ReservasListComponent },
  { path: 'nuevo', component: NuevoCocheComponent },
  { path: 'reservas/:id', component: EditarReservaComponent },
  { path: '**', redirectTo: '/login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
