/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;

import java.util.Date;

/**
 *
 * @author mi.arevalo10
 */
public class ItinerarioDTO
{
    private Long id;
    private String name;
    private Date fechaInicio;
    private Date fechaFin;
//    private ArrayList<CiudadDTO> ciudades;


    /**
     * Constructor por defecto
     */
    public ItinerarioDTO()
    {
    }

    /**
     * Constructor con parámetros.
     * @param id identificador del itinerario
     * @param name nombre del itinerario
     * @param fechaI
     * @param fechaF
     */
    public ItinerarioDTO(Long id, String name, Date fechaI, Date fechaF) {
		super();
		this.id = id;
		this.name = name;
                fechaInicio = fechaI;
                fechaFin = fechaF;
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the initial date
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param pFechaInicio fecha to set
     */
    public void setFechaInicio(Date pFechaInicio) {
        this.fechaInicio = pFechaInicio;
    }
   /**
     * @return the final date
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param pFechaFin date to set
     */
    public void setFechaFin(Date pFechaFin) {
        this.fechaFin = pFechaFin;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + "\" }" ;
    }
}
