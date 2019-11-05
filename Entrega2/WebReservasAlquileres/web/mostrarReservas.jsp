<%-- 
    Document   : mostrarReservas
    Created on : 07-oct-2019, 12:03:09
    Author     : cevp
--%>

<%@page import="Dominio.Reserva"%>
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
            <% List<Reserva> reservas = (List<Reserva>) session.getAttribute("reservasCli");
                int i = 0;
                for (Reserva item : reservas) {%>
            <tr>
                <td> <%= item.getMatricula()%></td>
                <td> <%= i%></td>

            </tr>
            <% i = i +1;
                }%>
        </table>
        <p> Introducir Id </p>
        <form action="controlador" name="ejemplo" method="get">
            Id: <input type="text" name="IdReserva"><br>
        <input type="submit" name="accion" value="Alquilar">
        </form>
    </body>
</html>
