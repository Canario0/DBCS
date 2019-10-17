/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.persistencia;

import ejb.dominio.Tipocarnet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prene
 */
@Stateless
public class TipocarnetFacade extends AbstractFacade<Tipocarnet> implements TipocarnetFacadeLocal {
    @PersistenceContext(unitName = "CompUsuarios-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipocarnetFacade() {
        super(Tipocarnet.class);
    }
    
}
