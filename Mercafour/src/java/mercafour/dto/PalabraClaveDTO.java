/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.dto;

/**
 *
 * @author Marco Hurtado
 */
public class PalabraClaveDTO {
    private Integer idPalabraClave;
    private String palabra;
    
    public PalabraClaveDTO(){}

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
}
