/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.dto;

import java.util.List;

/**
 *
 * @author Marco Hurtado
 */
public class CategoriaDTO {
    private Integer idCategoria;
    private String nombre;
    private List<ProductoDTO> productoList;
    private CategoriaDTO supercategoria;
    
    public CategoriaDTO(){}

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ProductoDTO> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDTO> productoList) {
        this.productoList = productoList;
    }

    public CategoriaDTO getSupercategoria() {
        return supercategoria;
    }

    public void setSupercategoria(CategoriaDTO supercategoria) {
        this.supercategoria = supercategoria;
    }
}
