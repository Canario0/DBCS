export interface Vehiculo {
  averiado: string;
  color: string;
  kilometraje: number;
  matricula: string;
  modelo: {
    año: number;
    costealquiler: number;
    fabricante: string;
    gps: string;
    idmodelo: number;
    nombre: string;
  };
}
