/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ejb.despliegue.CompFlotaFacadeLocal;
import ejb.despliegue.CompUsuariosFacadeLocal;
import ejb.dominio.Vehiculo;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Utils;

/**
 *
 * @author Prene
 */
@WebServlet(name = "ReservasController", urlPatterns = {"/ReservasController"})
public class ReservasController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ReservasController.class.getName());
    @EJB
    private CompUsuariosFacadeLocal usuarioFacade;
    @EJB
    private CompFlotaFacadeLocal flotaFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userNif = (String) request.getSession().getAttribute("NIF");
        String[] licencias = usuarioFacade.getLicencias(userNif);
        Date fechaInicio = Utils.parseDate((String) request.getParameter("fechaI"));
        Date fechaFin = Utils.parseDate((String) request.getParameter("fechaF"));
        LOGGER.log(Level.INFO, String.format("Procesando periodo reservar del usuario con nif %s con licencias %s con fecha inicio %s y fecha fin %s", userNif, Arrays.toString(licencias), fechaInicio.toString(), fechaFin.toString()));
        List <Vehiculo> vehiculos = flotaFacade.getVehiculos(licencias, fechaFin, fechaFin);
        if (vehiculos == null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.html");
            dispatcher.forward(request, response);
        }else{
            request.setAttribute("vehiculos", vehiculos);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/reservar.jsp");
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
