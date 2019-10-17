/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.persistencia;

import ejb.dominio.Tipocarnet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Prene
 */
@Local
public interface TipocarnetFacadeLocal {

    void create(Tipocarnet tipocarnet);

    void edit(Tipocarnet tipocarnet);

    void remove(Tipocarnet tipocarnet);

    Tipocarnet find(Object id);

    List<Tipocarnet> findAll();

    List<Tipocarnet> findRange(int[] range);

    int count();
    
}
