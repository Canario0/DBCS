/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import ejb.despliegue.CompUsuariosFacadeLocal;
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
public class CompUsuariosFacadeTest {
    
    static EJBContainer container;
    
    public CompUsuariosFacadeTest() {
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
     * Test of controlAccesos method, of class CompUsuariosFacade.
     */
    //@Ignore
    @Test
    public void testControlAccesos_Err_Login() throws Exception {
        System.out.println("controlAccesos Error en Login");
        String nombre = "Err";
        String clave = "Err";
        String tipoUsuario = "DaIgual";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        Boolean expResult = false;
        Boolean result = instance.controlAccesos(nombre, clave, tipoUsuario);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    //@Ignore
    @Test
    public void testControlAccesos_Err_Tipo() throws Exception {
        System.out.println("controlAccesos Error en Tipo");
        String nombre = "Adrmart";
        String clave = "123456";
        String tipoUsuario = "empleado";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        Boolean expResult = false;
        Boolean result = instance.controlAccesos(nombre, clave, tipoUsuario);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testControlAccesos_NoErr() throws Exception {
        System.out.println("controlAccesos acceso correcto");
        String nombre = "Adrmart";
        String clave = "123456";
        String tipoUsuario = "cliente";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        Boolean expResult = true;
        Boolean result = instance.controlAccesos(nombre, clave, tipoUsuario);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    /**
     * Test of getNIF method, of class CompUsuariosFacade.
     */
    //@Ignore
    @Test
    public void testGetNIF_NoExiste() throws Exception {
        System.out.println("getNIF No Existe");
        String nombre = "NoExiste";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        String expResult = null;
        String result = instance.getNIF(nombre);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testGetNIF_Existe() throws Exception {
        System.out.println("getNIF Existe");
        String nombre = "Adrmart";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        String expResult = "12418684H";
        String result = instance.getNIF(nombre);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testgetLicencias_Err() throws Exception {
        System.out.println("getLicencias Error");
        String NIF = "NoExiste";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        String expResult = null;
        String[] result = instance.getLicencias(NIF);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testgetLicencias_NoErr() throws Exception {
        System.out.println("getLicencias Sin Error");
        String NIF = "71151298Q";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        String expResult = "C";
        String result = instance.getLicencias(NIF)[1];
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testBloquedaro_Err() throws Exception {
        System.out.println("Bloqueado Error en el NIF");
        String NIF = "NoExiste";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        char expResult = 'E';
        char result = instance.bloqueado(NIF);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testBloquedaro_T() throws Exception {
        System.out.println("Bloqueado T");
        String NIF = "71151298Q";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        char expResult = 'T';
        char result = instance.bloqueado(NIF);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Ignore
    @Test
    public void testBloquedaro_F() throws Exception {
        System.out.println("Bloqueado T");
        String NIF = "12418684H";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CompUsuariosFacadeLocal instance = (CompUsuariosFacadeLocal)container.getContext().lookup("java:global/classes/CompUsuariosFacade");
        char expResult = 'F';
        char result = instance.bloqueado(NIF);
        assertEquals(expResult, result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
