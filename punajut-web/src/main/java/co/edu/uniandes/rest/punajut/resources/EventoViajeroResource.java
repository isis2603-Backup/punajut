/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;
import co.edu.uniandes.rest.punajut.dtos.EventoViajeroDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import co.edu.uniandes.punajut.api.IEventoViajeroLogic;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.rest.punajut.converters.EventoViajeroConverter;

import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author ls.hernandez10
 */
@Path("/viajeros/{idViajero}/itinerarios/{idItinerario}/visitasCiudad/{idVisitaCiudad}/eventosViajero")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class EventoViajeroResource
{
    	@Inject
	IEventoViajeroLogic eventoViajeroLogic;

        private static final Logger logger = Logger.getLogger(EventoViajeroResource.class.getName());


	/**
	 * Obtiene el listado de eventos a los cuales el viajero asistirÃ¡ en la ciudad
	 * @return lista de eventos
	 * @throws ItinerarioLogicException excepciÃ³n retornada por la lÃ³gica
	 */
    @GET
    public List<EventoViajeroDTO> getEventosViajero() throws ItinerarioLogicException
    {
        logger.info("Se ejecuta mÃ©todo getEventosViajero");
        return EventoViajeroConverter.listEntity2DTO(eventoViajeroLogic.getEventoViajeros());
    }

    /**
     * Obtiene una evento de la lista del viajero
     * @param id identificador del evento
     * @return evento encontrada
     * @throws ItinerarioLogicException cuando el evento no existe
     */
    @GET
    @Path("{id: \\d+}")
    public EventoViajeroDTO getEventoViajero(@PathParam("id") Long id) throws ItinerarioLogicException
    {
        return EventoViajeroConverter.fullEntity2DTO(eventoViajeroLogic.darEventoViajero(id));
    }

    /**
     * Agrega un evento a la lista del viajero
     * @param evento evento a agregar
     * @return datos del evento a agregar
     * @throws ItinerarioLogicException cuando ya existe un evento con el id suministrado
     */
    @POST
    public EventoViajeroDTO createEventoViajero(EventoViajeroDTO evento) throws ItinerarioLogicException {
        EventoViajeroEntity entity = EventoViajeroConverter.fullDTO2Entity(evento);
        return EventoViajeroConverter.fullEntity2DTO(eventoViajeroLogic.agregarEventoViajero(entity));
    }

    /**
     * Actualiza los datos de un evento en la lista del viajero
     * @param id identificador del evento a modificar
     * @param evento evento a modificar
     * @return datos del evento modificado
     * @throws ItinerarioLogicException cuando no existe una evento con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public EventoViajeroDTO updateEventoViajero(@PathParam("id") Long id, EventoViajeroDTO evento) throws ItinerarioLogicException {
        EventoViajeroEntity entity = EventoViajeroConverter.fullDTO2Entity(evento);
        entity.setId(id);
        EventoViajeroEntity oldEntity = eventoViajeroLogic.darEventoViajero(id);
        entity.setEvento(oldEntity.getEvento());
        return EventoViajeroConverter.fullEntity2DTO(eventoViajeroLogic.modificarEventoViajero(entity));
    }

    /**
     * Elimina los datos de un evento
     * @param id identificador del evento a eliminar
     * @throws ItinerarioLogicException cuando no existe un evento con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEventoCiudad(@PathParam("id") Long id) throws ItinerarioLogicException
    {
    	eventoViajeroLogic.eliminarEventoViajero(id);
    }
}
