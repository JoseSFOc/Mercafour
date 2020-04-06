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
    private List<PalabraClave> palabrasClave;
    private Categoria categoria;
    private Usuario propietario;
    private List<Comentario> comentarios;
    
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

    public List<PalabraClave> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(List<PalabraClave> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
