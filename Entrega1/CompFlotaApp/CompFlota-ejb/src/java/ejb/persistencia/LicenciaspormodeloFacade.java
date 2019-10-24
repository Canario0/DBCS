/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.persistencia;

import ejb.dominio.Licenciaspormodelo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prene
 */
@Stateless
public class LicenciaspormodeloFacade extends AbstractFacade<Licenciaspormodelo> implements LicenciaspormodeloFacadeLocal {
    @PersistenceContext(unitName = "CompFlota-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicenciaspormodeloFacade() {
        super(Licenciaspormodelo.class);
    }
    
}
