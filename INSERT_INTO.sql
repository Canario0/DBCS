INSERT INTO TIPOCARNET
VALUES
	('AM'),
	('A1'),
	('A2'),
	('A'),
	('B'),
	('BTP'),
	('BE'),
	('C1'),
	('C1E'),
	('C'),
	('CE'),
	('D1'),
	('D1E'),
	('D'),
	('DE');

INSERT INTO USUARIO
	(NIF,NOMBRE,PASSWORD,DIRECCIONELECTRONICA)
VALUES
	('12418684H', 'Adrmart', '123456', 'ligula.Aenean.gravida@Vestibulum.co.uk'),
	('71151298Q', 'Flannagan', 'JOHNWAYNE90', 'sheriff@desenfunda.net'),
	('71568294F', 'Oscfern', '654321', 'neque.non.quam@loremauctor.net'),
	('71138829L', 'admin', 'admin', 'admin@gmail.com'),
	('71173624D', 'malkomich', '007', 'malkomich@gmail.com');

INSERT INTO EMPLEADO
	(NIF,NUMEROEMPLEADO,FECHACONTRATACION,TIPOEMPLEADO)
VALUES
	('71568294F', '1', '2014-06-22', 'E'),
	('71138829L', '2', '2014-02-10', 'A');

INSERT INTO CLIENTE
	(NIF,BLOQUEADO)
VALUES
	('71151298Q', 'T'),
	('12418684H', 'F'),
	('71173624D', 'F');

INSERT INTO MODELO
	(IDMODELO,NOMBRE,FABRICANTE,COSTEALQUILER,AÑO,GPS) VALUES
('1','ActiveHybrid 7','bmw',59,2016,'F'),
('2', 'A3 e-tron','audi',68,2017,'T'),
('3','500e','Fiat',100,2018,'T'),
('4','A3 e-tron','audi',79,2018,'T'),
('5',' C-Max Energi','Ford',75,2019,'T');

INSERT INTO LICENCIA
	(NIF,tipo)
VALUES
	('71151298Q', 'B'),
	('71151298Q', 'D1'),
	('71151298Q', 'C'),
	('12418684H', 'C1E'),
	('71173624D', 'B');

INSERT INTO LICENCIASPORMODELO
	(IDMODELO,TIPO)
VALUES
	('2', 'B'),
	('1', 'B'),
	('3', 'C'),
	('4', 'B'),
	('5', 'B');

INSERT INTO VEHICULO
	(MATRICULA,COLOR,KILOMETRAJE,AVERIADO,IDMODELO)
VALUES
	('1234ZZZ', 'Azul', 11533, 'F', '1'),
	('9012ZZZ', 'Azul', 97807, 'T', '2'),
	('7042ZZZ', 'Blanco', 15467, 'F', '4'),
	('7082ZZZ', 'Rojo', 35467, 'F', '4'),
	('5678ZZZ', 'Negro', 43605, 'F', '3');

INSERT INTO RESERVA
	(IDRESERVA,FECHARESERVA,FECHAINICIOALQUILER,FECHAFINALQUILER,EJECUTADA,NIF,MATRICULA)
VALUES
	(1, '2018-12-21', '2018-12-22', '2019-01-13', 'T', '71151298Q', '1234ZZZ'),
	(2, '2019-01-01', '2019-01-11', '2019-01-12', 'T', '12418684H', '5678ZZZ'),
	(3, '2019-05-05', '2019-06-30', '2019-07-04', 'T', '12418684H', '7042ZZZ'),
	(4, '2019-08-13', '2019-09-01', '2019-09-04', 'F', '71173624D', '7042ZZZ'),
	(5, '2019-09-13', '2019-10-01', '2019-10-04', 'F', '12418684H', '1234ZZZ');

INSERT INTO ALQUILER
	(IDALQUILER,FECHAINICIO,FECHAFIN,KILOMETRAJESALIDA,MATRICULA,CLIENTE,REALIZADOPOR)
VALUES
	(1, '2018-12-22', '2019-01-13', 10021, '1234ZZZ', '71151298Q', '1'),
	(2, '2019-01-11', '2019-01-12', 41199, '5678ZZZ', '12418684H', '1'),
	(3, '2019-06-30', '2019-07-04', 10176, '7042ZZZ', '12418684H', '1');

