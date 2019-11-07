<%-- 
    Document   : reservar
    Created on : 07-oct-2019, 11:24:22
    Author     : cevp
--%>

<%@page import="Dominio.Vehiculo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Estan disponibles los siguientes vehículos:</h1>
        <table border="1px" style="text-align: center">
            <tr>
                <th style="width: 150px">Matricula</th>
                <th style="width: 50px">Km</th>
            </tr>
            <% List<Vehiculo> vehiculos = (List<Vehiculo>) session.getAttribute("vehiculos");
                for (Vehiculo item : vehiculos) {%>
            <tr>
                <td> <%= item.getMatricula()%></td>
                <td> <%= item.getKilometraje()%></td>

            </tr>
            <% }%>
        </table>
        <p> Introducir matricula y fechas</p>
        <form action="controlador" name="ejemplo" method="get">
            Matricula: <input type="text" name="matricula">
            Fecha inicial: <input type="text" name="fechaI">
            Fecha final: <input type="text" name="fechaF"><br>
        <input type="submit" name="accion" value="Reserva">
        </form>
    </body>
</html>
