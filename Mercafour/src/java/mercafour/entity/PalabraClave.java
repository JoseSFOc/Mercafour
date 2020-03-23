/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "PALABRA_CLAVE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PalabraClave.findAll", query = "SELECT p FROM PalabraClave p")
    , @NamedQuery(name = "PalabraClave.findByIdPalabraClave", query = "SELECT p FROM PalabraClave p WHERE p.idPalabraClave = :idPalabraClave")
    , @NamedQuery(name = "PalabraClave.findByPalabra", query = "SELECT p FROM PalabraClave p WHERE p.palabra = :palabra")})
public class PalabraClave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PALABRA_CLAVE")
    private Integer idPalabraClave;
    @Size(max = 50)
    @Column(name = "PALABRA")
    private String palabra;
    @JoinTable(name = "PRODUCTO_PALABRA_CLAVE", joinColumns = {
        @JoinColumn(name = "PALABRA_CLAVE", referencedColumnName = "ID_PALABRA_CLAVE")}, inverseJoinColumns = {
        @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID_PRODUCTO")})
    @ManyToMany
    private List<Producto> productoList;

    public PalabraClave() {
    }

    public PalabraClave(Integer idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
    }

    public Integer getIdPalabraClave() {
        return idPalabraClave;
    }

    public void setIdPalabraClave(Integer idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPalabraClave != null ? idPalabraClave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PalabraClave)) {
            return false;
        }
        PalabraClave other = (PalabraClave) object;
        if ((this.idPalabraClave == null && other.idPalabraClave != null) || (this.idPalabraClave != null && !this.idPalabraClave.equals(other.idPalabraClave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mercafour.entity.PalabraClave[ idPalabraClave=" + idPalabraClave + " ]";
    }
    
}
