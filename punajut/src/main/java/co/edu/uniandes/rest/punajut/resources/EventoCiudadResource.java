/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;
import co.edu.uniandes.rest.punajut.dtos.EventoCiudadDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import co.edu.uniandes.rest.punajut.mocks.EventoCiudadLogicMock;

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
 * @author r.cardenas11
 */
@Path("eventoCiudad")
public class EventoCiudadResource {
    	@Inject
	EventoCiudadLogicMock eventoCiudadLogic;

	/**
	 * Obtiene el listado de los eventos a los  en una ciudad.
	 * @return lista de eventos
	 * @throws ItinerarioLogicException excepción retornada por la lógica
	 */
    @GET
    public List<EventoCiudadDTO> getEventoCiudad() throws ItinerarioLogicException {
    //    return eventoCiudadLogic.getCities();
    return null;
    }

    /**
     * Obtiene uno de los eventos de la ciudad
     * @param id identificador del evento
     * @return evento encontrado
     * @throws ItinerarioLogicException cuando el evento no existe
     */
    @GET
    @Path("{id: \\d+}")
    public EventoCiudadDTO getCity(@PathParam("id") Long id) throws ItinerarioLogicException {
    //    return eventoCiudadLogic.getCity(id);
    return null;
    }

    /**
     * Registra un evento a la ciudad
     * @param evento evento a agregar
     * @return datos del evento a agregar
     * @throws ItinerarioLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public EventoCiudadDTO registrarEventoCiudad(EventoCiudadDTO evento) throws ItinerarioLogicException {
    //    return eventoCiudadLogic.createCity(evento);
    return null;
    }

    /**
     * Modifica un evento de la lista de la ciudad.
     * @param id identificador del evento a modificar
     * @param evento evento a modificar
     * @return datos del evento modificado
     * @throws ItinerarioLogicException cuando no existe un evento con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public EventoCiudadDTO modificarEventoCiudad(@PathParam("id") Long id, EventoCiudadDTO evento) throws ItinerarioLogicException {
    //    return eventoCiudadLogic.updateCity(id, evento);
    return null;
    }

    /**
     * Elimina los datos de un evento de la lista de la ciudad.
     * @param id identificador del evento a eliminar
     * @throws ItinerarioLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void borrarEventoCiudad(@PathParam("id") Long id) throws ItinerarioLogicException {
    //	eventoCiudadLogic.deleteCity(id);
    }

}
