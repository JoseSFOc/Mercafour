/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    
    public List<Producto> findByYearOnly (String year) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE YEAR(p.fecha) = :year");
        q.setParameter("year", new Integer(year)); 
        return q.getResultList();        
    }
    
    public List<Producto> findByMonthOnly (String month) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE MONTH(p.fecha) = :month");
        q.setParameter("month", new Integer(month)); 
        return q.getResultList();        
    }
    
    public List<Producto> findByDayOnly (String day) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE DAY(p.fecha) = :day");
        q.setParameter("day", new Integer(day)); 
        return q.getResultList();        
    }
    
    public List<Producto> findByYearAndMonth (String year, String month) {
        Query q;
        String fin;
        if (month.equals("2")) {
            fin = "28";
        } else if (month.equals("4") ||month.equals("6") ||month.equals("9") ||month.equals("11") ){
            fin = "30";
        }else{
            fin = "31";
        }
        String d1 = 1+"/"+month+"/"+year;
        String d2 = fin+"/"+month+"/"+year;
        Date f1 = null, f2 = null;
        try {
            f1 = new SimpleDateFormat("dd/MM/yyyy").parse(d1);
            f2 = new SimpleDateFormat("dd/MM/yyyy").parse(d2);
        } catch (ParseException ex) {
            Logger.getLogger(ProductoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.fecha BETWEEN :d1 AND :d2 ");
        q.setParameter("d1", f1);
        q.setParameter("d2", f2);
        
        return q.getResultList();         
    }
    
    public List<Producto> findByMonthAndDay (String month, String day) {        
        Query q;
        List<Producto> rdo = new ArrayList<>();
        int y1 = Calendar.getInstance().get(Calendar.YEAR)-20,y2 = Calendar.getInstance().get(Calendar.YEAR); 
        String d1; 
        Date f1 = null, f2 = null;
        while(y1<=y2){
            try {
                d1 = day+"/"+month+"/"+y1;
                f1 = new SimpleDateFormat("dd/MM/yyyy").parse(d1);
            } catch (ParseException ex) {
                Logger.getLogger(ProductoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
           q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.fecha = :d1");
           q.setParameter("d1", f1);
            if (!q.getResultList().isEmpty()) {
                rdo.addAll(q.getResultList());
            }
           y1++;
        }
        
        
        return rdo;
    }
    
    public List<Producto> findByDateFull (String year, String month, String day) {
        Query q;
        String df = day+"/"+month+"/"+year;
        Date f = null;
        try {
            f = new SimpleDateFormat("dd/MM/yyyy").parse(df);
        } catch (ParseException ex) {
            Logger.getLogger(ProductoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.fecha = :date");
        q.setParameter("date", f);
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
