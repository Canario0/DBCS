/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import ejb.despliegue.CompResAlqFacadeRemote;
import ejb.dominio.Reserva;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author cevp
 */
public class CompResAlqFacadeTest {

    static EJBContainer container;

    public CompResAlqFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        } catch (Exception e) {
            System.out.println("ERROR" + e.toString());
            fail("Fallo al crear el contenedor embebido");
        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addReserva method, of class CompResAlqFacade.
     */
    //@Ignore
    @Test
    public void test1_AddReserva() throws Exception {
        System.out.println("addReserva");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        //Nota: hemos modificado las fechas para evitar errores con nuestras validaciones
        Date fechaInicio = format.parse("2019-12-01");
        Date fechaFin = format.parse("2019-13-11");
        String NIF = "12418684H";
        String matricula = "9012ZZZ";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompResAlqFacadeRemote instance = (CompResAlqFacadeRemote) container.getContext().lookup("java:global/classes/CompResAlqFacade");
        Boolean expResult = true;
        Boolean result = instance.addReserva(fechaInicio, fechaFin, NIF, matricula);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getReservasF method, of class CompResAlqFacade.
     */
    //@Ignore
    @Test
    public void test2_GetReservasF_Error() throws Exception {
        System.out.println("getReservasF error en operacion");
        String NIF = "NoExiste";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompResAlqFacadeRemote instance = (CompResAlqFacadeRemote) container.getContext().lookup("java:global/classes/CompResAlqFacade");
        //List<Reserva> expResult = null;
        List<Reserva> result = instance.getReservasF(NIF);
        if (result.size() == 0) {
            assertTrue(true);
        } else {
            fail ("Error");
        }
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    //@Ignore
    @Test
    public void test3_GetReservasF_NoError() throws Exception {
        System.out.println("getReservasF comprobamos la reserva inicial añadida");
        String NIF = "12418684H";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompResAlqFacadeRemote instance = (CompResAlqFacadeRemote) container.getContext().lookup("java:global/classes/CompResAlqFacade");
        String expResult = "1234ZZZ";
        Boolean result = false;
        List<Reserva> reservasF = instance.getReservasF(NIF);
        if (reservasF.size() == 0) {
            fail("La lista de reservas esta vacia");
        } else {
            for (Reserva reservasF1 : reservasF) {
                if (reservasF1.getMatricula().equals(expResult)) {
                    result = true;
                }
            }
            assertTrue(result);
        }

        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addAlquiler method, of class CompResAlqFacade.
     */
    //@Ignore
    @Test
    public void test4_testAddAlquiler_Err() throws Exception {
        System.out.println("addAlquiler con error");
        int idReserva = 10000;
        float Km = 0.0F;
        String idEmpleado = "1";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompResAlqFacadeRemote instance = (CompResAlqFacadeRemote) container.getContext().lookup("java:global/classes/CompResAlqFacade");
        Boolean expResult = false;
        Boolean result = instance.addAlquiler(idReserva, Km, idEmpleado);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void test5_AddAlquiler_NoErr() throws Exception {
        System.out.println("addAlquiler sin error");
        int idReserva = 4;
        float Km = 0.0F;
        String idEmpleado = "1";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompResAlqFacadeRemote instance = (CompResAlqFacadeRemote) container.getContext().lookup("java:global/classes/CompResAlqFacade");
        Boolean expResult = true;
        Boolean result = instance.addAlquiler(idReserva, Km, idEmpleado);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
