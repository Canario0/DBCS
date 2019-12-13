/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import ejb.despliegue.CompFlotaFacadeLocal;
import ejb.dominio.Vehiculo;
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
public class CompFlotaFacadeTest {

    static EJBContainer container;

    public CompFlotaFacadeTest() {
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
     * Test of getVehiculos method, of class CompFlotaFacade.
     */
    //@Ignore
    @Test
    public void testGetVehiculos_todos() throws Exception {
        System.out.println("getVehiculos: todos disponibles y carnet para todos");
        String[] Licencias = {"B", "C"};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date FechaIni = format.parse("2019-08-10");
        Date FechaFin = format.parse("2019-08-20");
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompFlotaFacadeLocal instance = (CompFlotaFacadeLocal) container.getContext().lookup("java:global/ejb-app/classes/CompFlotaFacade");
        // matriculas originales
        // String[] matriculas = {"1234ZZZ", "9012ZZZ", "7042ZZZ", "7082ZZZ", "5678ZZZ"};
        // matriculas quitando los coches que se encuentran averiados
        String[] matriculas = {"1234ZZZ", "7042ZZZ", "7082ZZZ", "5678ZZZ"};
        List<Vehiculo> vehiculos = instance.getVehiculos(Licencias, FechaIni, FechaFin);
        Boolean result = true;
        for (int i = 0; i < matriculas.length; i++) {
            Boolean esta = false;
            for (Vehiculo vehiculo : vehiculos) {
                if (vehiculo.getMatricula().equals(matriculas[i])) {
                    esta = true;
                    break;
                }
            }
            if (!esta) {
                result = false;
                break;
            }
        }
        assertTrue(result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    //@Ignore
    @Test
    public void testGetVehiculos_UnoReservado() throws Exception {
        System.out.println("getVehiculos: el 7042ZZZ esta reservado");
        String[] Licencias = {"B", "C"};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date FechaIni = format.parse("2019-08-30");
        Date FechaFin = format.parse("2019-09-03");
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompFlotaFacadeLocal instance = (CompFlotaFacadeLocal) container.getContext().lookup("java:global/ejb-app/classes/CompFlotaFacade");
        String matricula = "7042ZZZ";
        List<Vehiculo> vehiculos = instance.getVehiculos(Licencias, FechaIni, FechaFin);
        Boolean result = true;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                result = false;
                break;
            }
        }
        assertTrue(result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testGetVehiculos_UnoSinCarnet() throws Exception {
        System.out.println("getVehiculos: no se tiene carnet para el 5678ZZZ");
        String[] Licencias = {"B", "C1"};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date FechaIni = format.parse("2019-08-10");
        Date FechaFin = format.parse("2019-08-20");
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompFlotaFacadeLocal instance = (CompFlotaFacadeLocal) container.getContext().lookup("java:global/ejb-app/classes/CompFlotaFacade");
        String matricula = "5678ZZZ";
        List<Vehiculo> vehiculos = instance.getVehiculos(Licencias, FechaIni, FechaFin);
        Boolean result = true;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                result = false;
                break;
            }
        }
        assertTrue(result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addVehiculo method, of class CompFlotaFacade.
     */
    //@Ignore
    @Test
    public void testAddVehiculo_Error() throws Exception {
        System.out.println("addVehiculo: error en la operacion");
        String idModelo = "NoExiste";
        String matricula = "0000ZZZ";
        String color = "Rojo";
        float Km = 0.0F;
        char averiado = 'F';
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompFlotaFacadeLocal instance = (CompFlotaFacadeLocal) container.getContext().lookup("java:global/ejb-app/classes/CompFlotaFacade");
        Boolean expResult = false;
        Boolean result = instance.addVehiculo(idModelo, matricula, color, Km, averiado);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testAddVehiculo_NoError() throws Exception {
        System.out.println("addVehiculo: sin error en la operacion");
        String idModelo = "5";
        String matricula = "0000ZZZ";
        String color = "Rojo";
        float Km = 0.0F;
        char averiado = 'F';
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompFlotaFacadeLocal instance = (CompFlotaFacadeLocal) container.getContext().lookup("java:global/ejb-app/classes/CompFlotaFacade");
        Boolean expResult = true;
        Boolean result = instance.addVehiculo(idModelo, matricula, color, Km, averiado);
        assertEquals(expResult, result);
        
        //Comprobacion de que se ha añadido
        String[] Licencias = {"B", "C"};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date FechaIni = format.parse("2019-08-10");
        Date FechaFin = format.parse("2019-08-20");
        List<Vehiculo> vehiculos = instance.getVehiculos(Licencias, FechaIni, FechaFin);
        result = false;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                result = true;
                break;
            }
        }
        // Este paso se ha añadido para evitar que el test falle en la siguiente ejecución
        instance.delVehiculo(matricula);
        assertTrue(result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delVehiculo method, of class CompFlotaFacade.
     */
    //@Ignore
    @Test
    public void testDelVehiculo_ERROR() throws Exception {
        System.out.println("delVehiculo con error. El vehiculo no existe");
        String matricula = "NoExiste";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompFlotaFacadeLocal instance = (CompFlotaFacadeLocal) container.getContext().lookup("java:global/ejb-app/classes/CompFlotaFacade");
        Boolean expResult = false;
        Boolean result = instance.delVehiculo(matricula);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testDelVehiculo_NoERROR() throws Exception {
        System.out.println("delVehiculo sin error.");
        String matricula = "0001ZZZ";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompFlotaFacadeLocal instance = (CompFlotaFacadeLocal) container.getContext().lookup("java:global/ejb-app/classes/CompFlotaFacade");
        String idModelo = "1";
        String color = "Rojo";
        float Km = 0.0F;
        char averiado = 'F';
        Boolean result = instance.addVehiculo(idModelo, matricula, color, Km, averiado);
        assertTrue(result);
        Boolean expResult = true;
        result = instance.delVehiculo(matricula);
        assertEquals(expResult, result);
        result = instance.delVehiculo(matricula);
        assertFalse(result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
