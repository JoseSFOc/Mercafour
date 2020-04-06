/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mercafour.dao.ComentarioFacade;
import mercafour.dao.ProductoFacade;
import mercafour.dao.UsuarioFacade;
import mercafour.dto.ComentarioDTO;
import mercafour.dto.ProductoDTO;
import mercafour.entity.Comentario;
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
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private ComentarioFacade comentarioFacade;

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
    
    public boolean nuevoComentario(String productoId, String texto, String valoracion, String autor, String fecha){
        Producto producto = this.productoFacade.find(new Integer(productoId));
        if (producto == null) { //Esta situación no debería darse
            LOG.log(Level.SEVERE, "No se ha encontrado el producto");
            return false;
        } else {
            if (this.usuarioFacade.findByName(autor) == null) {
                LOG.log(Level.SEVERE, "No existe el usuario");
                return false;
            } else {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                //ComentarioDTO comentario = new ComentarioDTO();
                Comentario comentario = new Comentario();
                comentario.setTexto(texto);
                //comentario.setAutor(this.usuarioFacade.findByName(autor).getDTO());
                comentario.setUsuario(usuarioFacade.findByName(autor));
                try {
                    comentario.setFecha(format.parse(fecha));
                } catch (ParseException ex) {
                    LOG.log(Level.SEVERE, "Formato de fecha incorrecto");
                }
                //comentario.setProducto(producto.getDTO());
                comentario.setProducto1(producto);
                comentario.setValoracion(new Integer(valoracion));
                
                this.comentarioFacade.create(comentario);
                return true;
            }
            
        }
    }

}
