/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mercafour.dao.CategoriaFacade;
import mercafour.dto.CategoriaDTO;
import mercafour.entity.Categoria;

/**
 *
 * @author Marco Hurtado
 */
@Stateless
public class CategoriasService {

    @EJB
    private CategoriaFacade categoriaFacade;
    
    protected List<CategoriaDTO> convertToDTO (List<Categoria> listaCateg) {
        List<CategoriaDTO> listaDTO = null;
        if (listaCateg != null) {
            listaDTO = new ArrayList<>();
            for (Categoria c: listaCateg) {
                listaDTO.add(c.getDTO());
            }
        }
        return listaDTO;
    }    
    
    public List<CategoriaDTO> searchAll () {
        List<Categoria> listaCateg = this.categoriaFacade.findAll();        
        return this.convertToDTO(listaCateg);
    }
}
