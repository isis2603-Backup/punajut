/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;

import co.edu.uniandes.rest.punajut.mocks.CiudadLogicMock;
import co.edu.uniandes.rest.punajut.dtos.CiudadDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;


import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author ls.hernandez10
 */
@Path("ciudades")
@Produces("application/json")
public class CiudadResource
{
    @Inject
	CiudadLogicMock cityLogic;

	/**
	 * Obtiene el listado de ciudades.
	 * @return lista de ciudades
	 * @throws ItinerarioLogicException excepción retornada por la lógica
	 */
    @GET
    public List<CiudadDTO> getCities() throws ItinerarioLogicException
    {
        return cityLogic.getCities();
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws ItinerarioLogicException cuando la ciudad no existe
     */
    @GET
    @Path("{id: \\d+}")
    public CiudadDTO getCity(@PathParam("id") Long id) throws ItinerarioLogicException
    {
        return cityLogic.getCity(id);
    }

    /**
     * Agrega una ciudad
     * @param city ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws ItinerarioLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public CiudadDTO createCity(CiudadDTO city) throws ItinerarioLogicException {
        return cityLogic.createCity(city);
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param city ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws ItinerarioLogicException cuando no existe una ciudad con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public CiudadDTO updateCity(@PathParam("id") Long id, CiudadDTO city) throws ItinerarioLogicException {
        return cityLogic.updateCity(id, city);
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws ItinerarioLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCity(@PathParam("id") Long id) throws ItinerarioLogicException {
    	cityLogic.deleteCity(id);
    }
}
