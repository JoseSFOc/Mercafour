/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mercafour.dao.ProductoFacade;
import mercafour.dto.ProductoDTO;
import mercafour.entity.Producto;

/**
 *
 * @author Marco Hurtado
 */
@Stateless
public class ProductosService {
    
    private static final Logger LOG = Logger.getLogger(ProductosService.class.getName());

    @EJB
    private ProductoFacade productoFacade;
    
    protected List<ProductoDTO> convertToDTO (List<Producto> listaProductos) {
        List<ProductoDTO> listaDTO = null;
        if (listaProductos != null) {
            listaDTO = new ArrayList<>();
            for (Producto producto: listaProductos) {
                listaDTO.add(producto.getDTO());
            }
        }
        return listaDTO;
    }
    
    public List<ProductoDTO> searchByDateDesc () {
        List<Producto> productos = this.productoFacade.findByDateDesc();
        List<ProductoDTO> rdo = new ArrayList<>();
        for (Producto producto : productos) {
            rdo.add(producto.getDTO());
        }
        return rdo;
    }
}
