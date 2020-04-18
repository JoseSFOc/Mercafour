/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mercafour.dao.CategoriaFacade;
import mercafour.dao.ComentarioFacade;
import mercafour.dao.PalabraClaveFacade;
import mercafour.dao.ProductoFacade;
import mercafour.dao.UsuarioFacade;
import mercafour.dto.ComentarioDTO;
import mercafour.dto.ProductoDTO;
import mercafour.entity.Categoria;
import mercafour.entity.Comentario;
import mercafour.entity.ComentarioPK;
import mercafour.entity.PalabraClave;
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

    @EJB
    private CategoriaFacade categoriaFacade;
    
    @EJB
    private PalabraClaveFacade palabraClaveFacade;

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
    
    public List<ProductoDTO> searchAll () {
        List<Producto> lista = this.productoFacade.findAll();        
        return this.convertToDTO(lista);
    } 

    public List<ProductoDTO> searchByDateDesc() {
        List<Producto> productos = this.productoFacade.findByDateDesc();
        List<ProductoDTO> rdo = new ArrayList<>();
        for (Producto producto : productos) {
            rdo.add(producto.getDTO());
        }
        return rdo;
    }

    public List<ProductoDTO> searchByUser(Usuario user) {
        List<Producto> productos = this.productoFacade.findAll();
        List<ProductoDTO> rdo = new ArrayList<>();
        for (Producto producto : productos) {
            if (user.equals(producto.getPropietario())) {
                rdo.add(producto.getDTO());
            }
        }
        return rdo;
    }

    public ProductoDTO searchById(String str) {
        Producto producto = this.productoFacade.find(new Integer(str));
        if (producto != null) {
            return producto.getDTO();
        } else {
            return null;
        }
    }

    public void createOrUpdate(String idProducto, String nombre, String descripcion,
            String precio, String imagen, String idPropietario,
            String idCategoria, String pClave) {

        Producto producto;
        boolean esCrear = false;
        Usuario propietario;
        Categoria categoria;
        List<PalabraClave> palabrasClave = new ArrayList<>();
        PalabraClave aux; 

        if (idProducto == null || idProducto.isEmpty()) {
            producto = new Producto(0);
            esCrear = true;
        } else {
            producto = this.productoFacade.find(new Integer(idProducto));
        }
        
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(new BigDecimal(precio));
        producto.setFecha(new Date());
        producto.setImagen(imagen);
        
        producto.setPalabraClaveList(new ArrayList<>());
        //
        propietario = this.usuarioFacade.find(new Integer(idPropietario));
        producto.setPropietario(propietario);

        categoria = this.categoriaFacade.find(new Integer(idCategoria));
        producto.setCategoria(categoria);

        if (esCrear) {
            this.productoFacade.create(producto); //creamos el producto y lo guardamos en la bd
        } /*else {
            this.productoFacade.edit(producto);
        }*/
        
        //Palabras clave
        //if pclave!=null || !pclave.isEmpty()
        StringTokenizer st = new StringTokenizer(pClave, "[,]");
        while(st.hasMoreTokens()){
            aux =null;
            String texto = st.nextToken();
            System.out.println(texto);
            int i = 0;
            while(i < texto.length() && texto.charAt(i) == ' '){i++;}
            if (i < texto.length()) { //no se meten espacios entre comas
                System.out.println(texto.substring(i));
                texto = texto.substring(i);
                List<Producto> pl = new ArrayList<>();
                aux = this.palabraClaveFacade.findByWord(texto);

                if(aux == null){//creamos palabra
                    aux = new PalabraClave(0); //creamos la palabra clave
                    aux.setPalabra(texto);
                    pl.add(producto); 
                    aux.setProductoList(pl); //le asociamos el producto
                    this.palabraClaveFacade.create(aux); //la guardamos en la bd
                } else {
                    if (!aux.getProductoList().contains(producto)) {
                        aux.getProductoList().add(producto);//asociamos el producto
                    }
                }
                palabrasClave.add(aux); //añadimos la palabra a las palabras que tendrá el producto
                //producto.getPalabraClaveList().add(aux);
            }
            //System.out.println(texto);
            producto.setPalabraClaveList(palabrasClave); //actualizamos el objeto producto
            this.productoFacade.edit(producto); //guardamos cambios
        }
        
        
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

    public boolean nuevoComentario(String productoId, String texto, String valoracion, String autor, String fecha) {
        Producto producto = this.productoFacade.find(new Integer(productoId));
        if (producto == null) { //Esta situación no debería darse
            LOG.log(Level.SEVERE, "No se ha encontrado el producto");
            return false;
        } else {
            if (this.usuarioFacade.findByEmail(autor) == null) {
                LOG.log(Level.SEVERE, "No existe el usuario");
                return false;
            } else {
                Usuario u = usuarioFacade.findByEmail(autor);
                ComentarioPK c= new ComentarioPK();
                c.setAutor(u.getIdUsuario());
                c.setProducto(producto.getIdProducto());
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                //ComentarioDTO comentario = new ComentarioDTO();
                Comentario comentario = new Comentario();
                comentario.setComentarioPK(c);
                comentario.setTexto(texto);
                //comentario.setAutor(this.usuarioFacade.findByName(autor).getDTO());
                comentario.setUsuario(u);
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
    
    public List<ComentarioDTO> buscarComentarios(String productoId){
        List<Comentario> lista = null;
        List<ComentarioDTO> rdo = null;
        Producto producto = this.productoFacade.find(new Integer(productoId));
        if (producto == null) {
            LOG.log(Level.SEVERE, "No existe el producto");
        } else {
            rdo = new ArrayList<>();
            lista = this.comentarioFacade.findByProductId(productoId);
            for (Comentario c : lista) {
                rdo.add(c.getDTO());
            }
        }
        return rdo;
    }
    
    public List<ProductoDTO> filtrar(String day, String month, String year, String categoria, String busquedaLibre){
        List<Producto> productosFecha, productosCategoria;
        List<ProductoDTO> rdo = new ArrayList<>();
        List<PalabraClave> coincidencias;
        Set<Producto> setFecha = new HashSet<>(), setCateg = new HashSet<>(), setRdo = new HashSet<>(), productosPalabrasClave;
        //fecha
        productosFecha = this.filtrarPorFecha(day, month, year);
        for (Producto producto : productosFecha) {setFecha.add(producto);}
        //categoria
        if (categoria!=null && !categoria.isEmpty()) {
            Categoria c = this.categoriaFacade.findById(categoria);
            productosCategoria = this.productoFacade.findByCategory(c);
        }else{
            productosCategoria = this.productoFacade.findAll();
        }
        for (Producto producto : productosCategoria) {setCateg.add(producto);}

        setRdo.addAll(setFecha);
        setRdo.retainAll(setCateg);
        
        //palabras clave
        productosPalabrasClave = setRdo;
        if (busquedaLibre != null && !busquedaLibre.isEmpty()) {
            coincidencias = this.coincidencias(busquedaLibre);
            setRdo.retainAll(filtrarPorPalabrasClave(coincidencias, productosPalabrasClave));
        }
        
        
        
        for (Producto producto : setRdo) {
            rdo.add(producto.getDTO());
        }
        
        return rdo;
    }
    
    protected List<Producto> filtrarPorFecha(String day, String month, String year){
        List<Producto> productos;
        if (day!=null && !day.isEmpty() && month !=null && !month.isEmpty() && year !=null && !year.isEmpty()) {
            productos = this.productoFacade.findByDateFull(year, month, day);
        } else if(month!=null && !month.isEmpty() && year !=null && !year.isEmpty()){
            productos = this.productoFacade.findByYearAndMonth(year, month);
        }else if(day!=null && !day.isEmpty() && month !=null && !month.isEmpty()){
            productos = this.productoFacade.findByMonthAndDay(month, day);
        }else if(day!=null && !day.isEmpty() && year !=null && !year.isEmpty()){
            productos = this.productoFacade.findByYearAndDay(year, day);
        }else if(year !=null && !year.isEmpty()){
            productos = this.productoFacade.findByYearOnly(year);
        }else if (month !=null && !month.isEmpty()) {
            productos = this.productoFacade.findByMonthOnly(month);
        }else if (day!=null && !day.isEmpty()) {
            productos = this.productoFacade.findByDayOnly(day);
        }else{
           productos = this.productoFacade.findByDateDesc(); 
        }
        return productos;
    }
    /* Búqueda con filtros:
    un método en facade para cada filtro individual
    un método de búsqueda con filtros que tome array de palabras (1 por filtro) y vaya cada método individual.
    */
    
    protected List<PalabraClave> coincidencias(String busquedaLibre){
        int i = 0;
        StringTokenizer strtok = new StringTokenizer(busquedaLibre, "[,]");
        List<String> busqueda = new ArrayList<>();
        List<String> palabrasClave = new ArrayList<>();
        List<PalabraClave>rdo = new ArrayList<>();
        //for (PalabraClave p : this.palabraClaveFacade.findAll()) { palabrasClave.add(p.getPalabra().toUpperCase());}
        while (strtok.hasMoreTokens()) { busqueda.add(strtok.nextToken());}
        for (String p : busqueda) {
            PalabraClave pk = this.palabraClaveFacade.findByWord(p);
            if (pk!=null) {
                rdo.add(pk);
            }
        }
        return rdo;
    }
    
    private Set<Producto> filtrarPorPalabrasClave(List<PalabraClave> busqueda, Set<Producto> productos) {
        Set<Producto> rdo = new HashSet<>();
        int i;
        for (Producto producto : productos) {
            i = 0;
            while(i < busqueda.size() && !producto.getPalabraClaveList().contains(busqueda.get(i))){
                i++;
            }
            if (i < busqueda.size()) {
                rdo.add(producto);
            }
        }
        return rdo;
    }
    
    private Set<Producto> filtrarPorTitulo(List<String> palabras, Set<Producto> productos) {
        Set<Producto> rdo = new HashSet<>();
        List<String> busqueda = new ArrayList<>();
        for (String pal : palabras) { busqueda.add(pal.toUpperCase());}
        int i;
        for (Producto producto : productos) {
            i = 0;
            while(i < busqueda.size() && (!producto.getNombre().toUpperCase().contains(busqueda.get(i)) 
                    || !producto.getDescripcion().toUpperCase().contains(busqueda.get(i)))){
                i++;
            }
            if (i < busqueda.size()) {
                rdo.add(producto);
            }
        }
        return rdo;
    }

}
