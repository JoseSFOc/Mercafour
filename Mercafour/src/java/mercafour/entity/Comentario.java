/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import mercafour.dto.ComentarioDTO;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "COMENTARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c")
    , @NamedQuery(name = "Comentario.findByTexto", query = "SELECT c FROM Comentario c WHERE c.texto = :texto")
    , @NamedQuery(name = "Comentario.findByValoracion", query = "SELECT c FROM Comentario c WHERE c.valoracion = :valoracion")
    , @NamedQuery(name = "Comentario.findByFecha", query = "SELECT c FROM Comentario c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Comentario.findByAutor", query = "SELECT c FROM Comentario c WHERE c.comentarioPK.autor = :autor")
    , @NamedQuery(name = "Comentario.findByProducto", query = "SELECT c FROM Comentario c WHERE c.comentarioPK.producto = :producto")})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComentarioPK comentarioPK;
    //@Size(max = 200)
    @Column(name = "TEXTO", length = 200)
    private String texto;
    @Column(name = "VALORACION")
    private Integer valoracion;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID_PRODUCTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto1;
    @JoinColumn(name = "AUTOR", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Comentario() {
    }

    public Comentario(ComentarioPK comentarioPK) {
        this.comentarioPK = comentarioPK;
    }

    public Comentario(int autor, int producto) {
        this.comentarioPK = new ComentarioPK(autor, producto);
    }

    public ComentarioPK getComentarioPK() {
        return comentarioPK;
    }

    public void setComentarioPK(ComentarioPK comentarioPK) {
        this.comentarioPK = comentarioPK;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto1() {
        return producto1;
    }

    public void setProducto1(Producto producto1) {
        this.producto1 = producto1;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comentarioPK != null ? comentarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.comentarioPK == null && other.comentarioPK != null) || (this.comentarioPK != null && !this.comentarioPK.equals(other.comentarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mercafour.entity.Comentario[ comentarioPK=" + comentarioPK + " ]";
    }
    
    public ComentarioDTO getDTO(){
        ComentarioDTO commentDTO = new ComentarioDTO();
        commentDTO.setAutor(this.usuario.getDTO());
        commentDTO.setFecha(this.fecha);
        commentDTO.setProducto(this.comentarioPK.getProducto());
        commentDTO.setTexto(this.texto);
        commentDTO.setValoracion(this.valoracion);
        return commentDTO;
    }
    
}
