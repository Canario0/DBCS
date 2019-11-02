/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.persistencia;

import ejb.dominio.Licenciaspormodelo;
import ejb.dominio.Modelo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public List<String> findByIdModelo(Modelo idModelo) {
        Query query = getEntityManager().createNamedQuery("Licenciaspormodelo.findLicenciaByModeloId");
        query.setParameter("idmodelo",idModelo);
        return query.getResultList();
    }
    
}
