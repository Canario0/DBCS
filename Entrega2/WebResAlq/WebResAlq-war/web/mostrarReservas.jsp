<%-- 
    Document   : mostrarReservas
    Created on : 07-oct-2019, 12:03:09
    Author     : cevp
--%>

<%@page import="ejb.dominio.Reserva"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reservas del cliente</h1>
        <table border="1px" style="text-align: center">
            <tr>
                <th style="width: 150px">Matricula</th>
                <th style="width: 50px">Id</th>
            </tr>
            <% List<Reserva> reservas = (List<Reserva>) request.getAttribute("reservasCli");
                int i = 0;
                for (Reserva item : reservas) {%>
            <tr>
                <td> <%= item.getMatricula()%></td>
                <td> <%= item.getIdreserva() %></td>

            </tr>
            <% i = i +1;
                }%>
        </table>
        <p> Introducir Id </p>
        <form action="MostrarReservaController" name="ejemplo" method="get">
            Id: <input type="text" name="IdReserva"><br>
            Id Empleado: <input type="text" name="IdEmpleado"><br>
            Km: <input type="text" name="KM"><br>
        <input type="submit" name="accion" value="Alquilar">
        </form>
    </body>
</html>
