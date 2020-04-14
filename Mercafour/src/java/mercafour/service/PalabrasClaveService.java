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
import mercafour.dao.PalabraClaveFacade;
import mercafour.dto.CategoriaDTO;
import mercafour.dto.PalabraClaveDTO;
import mercafour.entity.Categoria;
import mercafour.entity.PalabraClave;

/**
 *
 * @author Marco Hurtado
 */
@Stateless
public class PalabrasClaveService {

    @EJB
    private PalabraClaveFacade palabraClaveFacade;
    
    protected List<PalabraClaveDTO> convertToDTO (List<PalabraClave> listaPalabrasClave) {
        List<PalabraClaveDTO> listaDTO = null;
        if (listaPalabrasClave != null) {
            listaDTO = new ArrayList<>();
            for (PalabraClave p: listaPalabrasClave) {
                listaDTO.add(p.getDTO());
            }
        }
        return listaDTO;
    }    
    
    public List<PalabraClaveDTO> searchAll () {
        List<PalabraClave> listaPalabrasClave = this.palabraClaveFacade.findAll();        
        return this.convertToDTO(listaPalabrasClave);
    }
}
