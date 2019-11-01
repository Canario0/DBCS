/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.persistencia;

import ejb.dominio.Reserva;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ivan
 */
@Stateless
public class ReservaFacade extends AbstractFacade<Reserva> implements ReservaFacadeLocal {

    @PersistenceContext(unitName = "CompReservasAlquiler-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservaFacade() {
        super(Reserva.class);
    }

    @Override
    public List<Reserva> findF(String nif) {
        Query query = getEntityManager().createNamedQuery("Reserva.findByEjecutadaAndNif");
        query.setParameter("ejecutada", 'F');
        query.setParameter("nif", nif);
        return query.getResultList();

    }

    @Override
    public String[] findInDate(Date ini, Date fin) {
        Query query = getEntityManager().createNamedQuery("Reserva.findByRange");
        query.setParameter("ejecutada",'F');
        query.setParameter("fechaInicio", ini);
        query.setParameter("fechaFinal", fin);
        List<String> reservas = query.getResultList();
        if(reservas == null){
            return null;
        }
        
        String[] matriculas = new String[reservas.size()];
        for (int i = 0; i < reservas.size(); i++) {
            matriculas[i] = reservas.get(i);
        }
        
        return matriculas;
    }

}
