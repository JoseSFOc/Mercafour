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
import mercafour.entity.PalabraClave;

/**
 *
 * @author aleja
 */
@Stateless
public class PalabraClaveFacade extends AbstractFacade<PalabraClave> {

    @PersistenceContext(unitName = "MercafourPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PalabraClaveFacade() {
        super(PalabraClave.class);
    }
    
    public PalabraClave findByWord(String s){
        String palabra = s.toUpperCase();
        PalabraClave p = null;
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM PalabraClave p WHERE UPPER(p.palabra) LIKE :palabra");
        q.setParameter("palabra", palabra);

        if(q.getResultList()!= null && !q.getResultList().isEmpty()){
            p = (PalabraClave) q.getResultList().get(0);
        }
        return p;
    }
    
    public PalabraClave findSimilarWords(String s){
        String palabra = s.toUpperCase();
        PalabraClave p = null;
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM PalabraClave p WHERE UPPER(p.palabra) LIKE :palabra");
        q.setParameter("palabra", "%" + palabra + "%");

        if(q.getResultList()!= null && !q.getResultList().isEmpty()){
            p = (PalabraClave) q.getResultList().get(0);
        }
        return p;
    }
    
    
    
}
