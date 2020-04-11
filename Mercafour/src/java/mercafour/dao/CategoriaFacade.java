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
import mercafour.entity.Categoria;

/**
 *
 * @author aleja
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "MercafourPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    public Categoria findByName(String categoria){
        Query q;
        Categoria c = null;
        List<Categoria> lista;
        
        q = this.getEntityManager().createQuery("SELECT c FROM Categoria c WHERE c.nombre LIKE :nombre");
        q.setParameter("nombre", "%" + categoria + "%"); // Los % se ponen delante y detrás para indicar que la cadena
                                                      // de caracteres debe estar incluida en el nombre.
        //return q.getResultList();  
        /*
        q = this.getEntityManager().createNamedQuery("Usuario.findByName");
        q.setParameter("nombre", user);*/
        lista = q.getResultList();
        
        if(lista!= null && !lista.isEmpty()){
            c = lista.get(0);
        }
        
        return c;
    }
    
}
