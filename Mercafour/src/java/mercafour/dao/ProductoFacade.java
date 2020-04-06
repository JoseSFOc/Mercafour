/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mercafour.entity.Categoria;
import mercafour.entity.Producto;

/**
 *
 * @author aleja
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "MercafourPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public List<Producto> findByDate (String fecha) {
        Query q;
        
        Date date;
        try {
            date = DateFormat.getDateInstance().parse(fecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.fecha = :fecha");
        q.setParameter("fecha", fecha);
        return q.getResultList();        
    } 
    
    public List<Producto> findByDateDesc () {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p ORDER BY p.fecha DESC");
        return q.getResultList();        
    }  

    public List<Producto> findByName (String nombre) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre");
        q.setParameter("nombre", "%" + nombre + "%"); // Los % se ponen delante y detrás para indicar que la cadena
                                                      // de caracteres debe estar incluida en el nombre.
        return q.getResultList();        
    }
    
    public List<Producto> findByCategory (String categoria) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.categoria LIKE :categoria");
        q.setParameter("categoria", "%" + categoria + "%"); // Los % se ponen delante y detrás para indicar que la cadena
                                                      // de caracteres debe estar incluida en el nombre.
        return q.getResultList();        
    }
    
    /*
    public List<Producto> findByKeyWord(String categoria) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.categoria LIKE :categoria");
        q.setParameter("categoria", "%" + categoria + "%"); // Los % se ponen delante y detrás para indicar que la cadena
                                                      // de caracteres debe estar incluida en el nombre.
        return q.getResultList();        
    }        
    
    public List<Customer> findByMicroMarketAndName (String codigopostal, String nombre) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT c FROM Customer c WHERE c.zip.zipCode = :codigo AND c.name LIKE :nombre");
        q.setParameter("codigo", codigopostal);
        q.setParameter("nombre", "%" + nombre + "%"); // Los % se ponen delante y detrás para indicar que la cadena
                                                      // de caracteres debe estar incluida en el nombre.
        return q.getResultList();        
    }  
    */
}
