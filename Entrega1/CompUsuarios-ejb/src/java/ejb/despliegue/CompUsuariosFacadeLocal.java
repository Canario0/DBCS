/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import javax.ejb.Local;

/**
 *
 * @author pabrene
 */
@Local
public interface CompUsuariosFacadeLocal {

    /**
     * Comprueba si los datos de acceso son correctos.
     *
     * @param nombre nombre del usuario.
     * @param clave clave del usuario.
     * @param tipoUsuario tipo de usuario.
     * @return true si los datos de acesso son correctos, false en caso
     * contrario.
     */
    boolean controlAcceso(String nombre, String clave, String tipoUsuario);

    /**
     * Retorna el nif the un usuario
     *
     * @param nombre nombre de usuario
     * @return nif del usuario, en caso de error retorna null.
     */
    String getNIF(String nombre);

    /**
     * Comprueba si un cliente está bloqueado o no
     *
     * @param NIF nif del usuario
     * @return T si está bloqueado, F si no lo está en caso de error retornará
     * E.
     */
    char bloquedo(String NIF);

    /**
     * Obtener licencias que tiene asociada un usuario.
     *
     * @param NIF nif del usuario
     * @return array de los carnets que tiene asociado un usuario, null en caso
     * contrario.
     */
    String[] getLicencias(String NIF);
}
