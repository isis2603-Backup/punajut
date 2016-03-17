/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;

import co.edu.uniandes.rest.punajut.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import co.edu.uniandes.rest.punajut.mocks.ItinerarioLogicMock;


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
 * @author mi.arevalo10
 */
@Path("itinerarios")
@Produces("application/json")
public class ItinerarioResource {

    @Inject
    ItinerarioLogicMock itinerarioLogic;

    /**
     * Obtiene el listado de itinerarios.
     *
     * @return lista de itinerarios
     * @throws ItinerarioLogicException excepción retornada por la lógica
     */
    @GET
    public List<ItinerarioDTO> getItinerarios() throws ItinerarioLogicException{
        return null;
    }

    /**
     * Obtiene un  itinerario
     *
     * @param id identificador del itinerario
     * @return itinerario encontrada
     * @throws ItinerarioLogicException cuando el itinerario no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ItinerarioDTO getItinerario(@PathParam("id") Long id) throws ItinerarioLogicException {
        return itinerarioLogic.getItinerario(id);
    }

    /**
     * Agrega un itinerario
     *
     * @param itinerario itinerario a agregar
     * @return datos del itinerario a agregar
     * @throws ItinerarioLogicException cuando ya existe un itinerario con el id
     * suministrado
     */
    @POST
    public ItinerarioDTO createItinerario(ItinerarioDTO itinerario) throws ItinerarioLogicException {
        return itinerarioLogic.createItinerario(itinerario);
    }

    /**
     * Actualiza los datos de un itinerario
     *
     * @param id identificador del itinerario a modificar
     * @return el itinerario modificado
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id
     * suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ItinerarioDTO updateItinerario(@PathParam("id") Long id,  ItinerarioDTO pIt) throws ItinerarioLogicException {
        return itinerarioLogic.updateItinerario(id, pIt);
    }

    /**
     * Elimina un itinerario
     *
     * @param id identificador de itinerario a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id
     * suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) throws ItinerarioLogicException
    {
        itinerarioLogic.deleteItinerario(id);
    }

}
