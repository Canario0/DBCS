/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Licenciaspormodelo;
import dominio.Modelo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ivan
 */
@Local
public interface LicenciaspormodeloFacadeLocal {

    void create(Licenciaspormodelo licenciaspormodelo);

    void edit(Licenciaspormodelo licenciaspormodelo);

    void remove(Licenciaspormodelo licenciaspormodelo);

    Licenciaspormodelo find(Object id);

    List<Licenciaspormodelo> findAll();

    List<Licenciaspormodelo> findRange(int[] range);

    int count();
    
    List<String> findByIdModelo(Modelo idModelo);
    
}
