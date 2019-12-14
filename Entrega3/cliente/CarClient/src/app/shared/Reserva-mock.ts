import { Reserva } from './reserva';


export const RESERVAS: Reserva[] = [
  {
    id: 6,
    fechaReserva: new Date('2019-11-14'),
    fechaInicioAlquiler: new Date('2019-11-25'),
    fechaFinAlquiler: new Date('2019-11-28'),
    ejecutada: 'F',
    nif: '12418684H',
    matricula: '5678ZZZ'
  },
  {
    id: 7,
    fechaReserva: new Date('2019-11-14'),
    fechaInicioAlquiler: new Date('2019-12-06'),
    fechaFinAlquiler: new Date('2020-03-20'),
    ejecutada: 'F',
    nif: '12418684H',
    matricula: '7042ZZZ'
  }
];
