/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.punajut.rest.cities.resources;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author ls.hernandez10
 */
@Path("eventoVisual")
public class eventoVisualizacionResource {
    	@Inject
	CityLogicMock eventoVisualLogic;

	/**
	 * Obtiene el listado de los eventos.
	 * @return lista de ciudades
	 * @throws CityLogicException excepción retornada por la lógica
	 */
    @GET
    public List<CityDTO> getCities() throws CityLogicException {
        return eventoVisualLogic.getCities();
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CityLogicException cuando la ciudad no existe
     */
    @GET
    @Path("{id: \\d+}")
    public CityDTO getCity(@PathParam("id") Long id) throws CityLogicException {
        return eventoVisualLogic.getCity(id);
    }

    /**
     * Agrega un evento al itinerario desde el timeline
     * @param evento evento a agregar
     * @return datos del evento a agregar
     * @throws CityLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public CityDTO crearEventoDesdeTimeline(CityDTO evento) throws CityLogicException {
        return eventoVisualLogic.createCity(city);
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param city ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public CityDTO updateCity(@PathParam("id") Long id, CityDTO city) throws CityLogicException {
        return eventoVisualLogic.updateCity(id, city);
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCity(@PathParam("id") Long id) throws CityLogicException {
    	eventoVisualLogic.deleteCity(id);
    }

}
