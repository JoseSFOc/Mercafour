/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mercafour.dao.ProductoFacade;
import mercafour.dto.ProductoDTO;
import mercafour.entity.Producto;
import mercafour.entity.Usuario;

/**
 *
 * @author Marco Hurtado
 */
@Stateless
public class ProductosService {

    private static final Logger LOG = Logger.getLogger(ProductosService.class.getName());

    @EJB
    private ProductoFacade productoFacade;

    protected List<ProductoDTO> convertToDTO(List<Producto> listaProductos) {
        List<ProductoDTO> listaDTO = null;
        if (listaProductos != null) {
            listaDTO = new ArrayList<>();
            for (Producto producto : listaProductos) {
                listaDTO.add(producto.getDTO());
            }
        }
        return listaDTO;
    }

    public List<ProductoDTO> searchByDateDesc() {
        List<Producto> productos = this.productoFacade.findByDateDesc();
        List<ProductoDTO> rdo = new ArrayList<>();
        for (Producto producto : productos) {
            rdo.add(producto.getDTO());
        }
        return rdo;
    }

    public List<ProductoDTO> searchByUser(Usuario user){
        List<Producto> productos = this.productoFacade.findAll();
        List<ProductoDTO> rdo = new ArrayList<>();
        for(Producto producto : productos){
            if(user.equals(producto.getPropietario())){
                rdo.add(producto.getDTO());
            }
        }
        return rdo;
    }
    
    public boolean remove(String id) {   
        Producto producto = this.productoFacade.find(new Integer(id));
        if (producto == null) { //Esta situación no debería darse
            LOG.log(Level.SEVERE, "No se ha encontrado el producto a borrar");
            return false;
        } else {
            this.productoFacade.remove(producto);
            return true;
        }
    }

}
