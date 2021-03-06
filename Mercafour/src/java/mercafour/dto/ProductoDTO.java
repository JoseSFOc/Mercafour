/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import mercafour.entity.Categoria;
import mercafour.entity.Comentario;
import mercafour.entity.PalabraClave;
import mercafour.entity.Usuario;

/**
 *
 * @author Marco Hurtado
 */
public class ProductoDTO {
    
    private Integer productoId;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Date fecha;
    private String imagen;
    private List<PalabraClaveDTO> palabrasClave;
    private CategoriaDTO categoria;
    private UsuarioDTO propietario;
    private List<ComentarioDTO> comentarios;
    
    public ProductoDTO(){}

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<PalabraClaveDTO> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(List<PalabraClaveDTO> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public UsuarioDTO getPropietario() {
        return propietario;
    }

    public void setPropietario(UsuarioDTO propietario) {
        this.propietario = propietario;
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }
}
