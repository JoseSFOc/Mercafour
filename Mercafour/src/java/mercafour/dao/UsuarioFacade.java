/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mercafour.entity.Usuario;

/**
 *
 * @author aleja
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "MercafourPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    //Consulta para encontrar a los usuario por su email
    public Usuario findByEmail(String user){
        Query q;
        Usuario usuario = null;
        List<Usuario> lista;
        
        q = this.getEntityManager().createNamedQuery("Usuario.findByEmail");
        q.setParameter("email", user);
        lista = q.getResultList();
        
        if(lista!= null && !lista.isEmpty()){
            usuario = lista.get(0);
        }
        
        return usuario;
    }
    
}
