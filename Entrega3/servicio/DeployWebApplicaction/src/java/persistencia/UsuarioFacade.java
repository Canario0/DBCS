/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ivan
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "DeployWebApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findByUsuarioAndClave(String nombre, String clave) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByNombrePassword");
        query.setParameter("nombre", nombre);
        query.setParameter("password", clave);
        try {
            Usuario user;
            user = (Usuario) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String findByUsuario(String nombre) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByNombre");
        query.setParameter("nombre", nombre);
        try {
            Usuario user;
            user = (Usuario) query.getSingleResult();
            return user.getNif();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    
}
