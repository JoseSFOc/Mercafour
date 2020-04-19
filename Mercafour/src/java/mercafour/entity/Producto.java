/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mercafour.dto.ComentarioDTO;
import mercafour.dto.PalabraClaveDTO;
import mercafour.dto.ProductoDTO;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")
    , @NamedQuery(name = "Producto.findByFecha", query = "SELECT p FROM Producto p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Producto.findByImagen", query = "SELECT p FROM Producto p WHERE p.imagen = :imagen")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;
    //@Size(max = 50)
    @Column(name = "NOMBRE", length = 50)
    private String nombre;
    //@Size(max = 200)
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private BigDecimal precio;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    //@Size(max = 100)
    @Column(name = "IMAGEN", length = 100)
    private String imagen;
    @ManyToMany(mappedBy = "productoList", cascade = CascadeType.ALL)
    private List<PalabraClave> palabraClaveList;
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "ID_CATEGORIA")
    @ManyToOne
    private Categoria categoria;
    @JoinColumn(name = "PROPIETARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario propietario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto1")
    private List<Comentario> comentarioList;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
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

    @XmlTransient
    public List<PalabraClave> getPalabraClaveList() {
        return palabraClaveList;
    }

    public void setPalabraClaveList(List<PalabraClave> palabraClaveList) {
        this.palabraClaveList = palabraClaveList;
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

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mercafour.entity.Producto[ idProducto=" + idProducto + " ]";
    }
    
    public ProductoDTO getDTO(){
        List<ComentarioDTO> comentariosDTO = new ArrayList<>();
        for (Comentario c : this.comentarioList) {
            comentariosDTO.add(c.getDTO());
        }
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setCategoria(this.categoria.getDTO());
        productoDTO.setComentarios(comentariosDTO);
        productoDTO.setDescripcion(this.descripcion);
        productoDTO.setFecha(this.fecha);
        productoDTO.setImagen(this.imagen);
        productoDTO.setNombre(this.nombre);
        List<PalabraClaveDTO> listaPalabras = new ArrayList<>();
        if (this.palabraClaveList != null && !this.palabraClaveList.isEmpty()) {
            for (PalabraClave p : this.palabraClaveList) { listaPalabras.add(p.getDTO());}
        }
        productoDTO.setPalabrasClave(listaPalabras);
        productoDTO.setPrecio(this.precio);
        productoDTO.setProductoId(this.idProducto);
        productoDTO.setPropietario(this.propietario.getDTO()); 
        return productoDTO;
    }
    
}
