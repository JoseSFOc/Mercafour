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
import mercafour.dao.UsuarioFacade;
import mercafour.dto.UsuarioDTO;
import mercafour.entity.Usuario;

/**
 *
 * @author Marco Hurtado
 */
@Stateless
public class UsuariosService {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    protected List<UsuarioDTO> convertToDTO (List<Usuario> listaUsuarios) {
        List<UsuarioDTO> listaDTO = null;
        if (listaUsuarios != null) {
            listaDTO = new ArrayList<>();
            for (Usuario cliente: listaUsuarios) {
                listaDTO.add(cliente.getDTO());
            }
        }
        return listaDTO;
    }
    
    public List<UsuarioDTO> searchAll () {
        List<Usuario> listaDC = this.usuarioFacade.findAll();        
        return this.convertToDTO(listaDC);
    } 
    
    public UsuarioDTO searchById (String usuarioId) {
        Usuario user = this.usuarioFacade.find(new Integer(usuarioId));
        if (user != null) {
            return user.getDTO();
        } else {
            return null;
        }
    }
}
