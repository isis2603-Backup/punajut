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
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.rest.punajut.converters.EventoViajeroConverter;

import java.util.List;
import java.util.logging.Level;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
         * @param idViajero
         * @param idItinerario
         * @param idVisitaCiudad
	 * @return lista de eventos
	 * @throws ItinerarioLogicException excepciÃ³n retornada por la lÃ³gica
	 */
    @GET
    @Path("{idViajero: \\d+}/{idItinerario: \\d+}/{idVisitaCiudad: \\d+}")
    public List<EventoViajeroDTO> getEventosViajero(@PathParam("idViajero") Long idViajero, @PathParam("idItinerario") Long idItinerario, @PathParam("idVisitaCiudad") Long idVisitaCiudad) throws ItinerarioLogicException
    {
        logger.info("Se ejecuta mÃ©todo getEventosViajero");
        List<EventoViajeroEntity> eventosViajero = null;
        try
        {
            eventosViajero = eventoViajeroLogic.getEventosViajero(idViajero, idItinerario, idVisitaCiudad);
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(EventoViajeroResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EventoViajeroConverter.listEntity2DTO(eventosViajero);
    }

    /**
     * Obtiene una evento de la lista del viajero
     * @param idViajero
     * @param idItinerario
     * @param idVisitaCiudad
     * @param idEventoViajero identificador del evento
     * @return evento encontrada
     * @throws ItinerarioLogicException cuando el evento no existe
     */
    @GET
    @Path("{idViajero: \\d+}/{idItinerario: \\d+}/{idVisitaCiudad: \\d+}/{id: \\d+}")
    public EventoViajeroDTO getEventoViajero(@PathParam("idViajero") Long idViajero, @PathParam("idItinerario") Long idItinerario, @PathParam("idVisitaCiudad") Long idVisitaCiudad, @PathParam("id") Long idEventoViajero) throws ItinerarioLogicException
    {
        EventoViajeroEntity eventoViajero = null;
        try
        {
            eventoViajero = eventoViajeroLogic.getEventoViajero(idViajero, idItinerario, idVisitaCiudad, idEventoViajero);
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(EventoViajeroResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EventoViajeroConverter.fullEntity2DTO(eventoViajero);
    }

    /**
     * Agrega un evento a la lista del viajero
     * @param evento evento a agregar
     * @param idViajero
     * @param idItinerario
     * @param idVisitaCiudad
     * @return eventoViajeroDTO
     */
    @POST
    @Path("{idViajero: \\d+}/{idItinerario: \\d+}/{idVisitaCiudad: \\d+}")
    public EventoViajeroDTO createEventoViajero(@PathParam("idViajero") Long idViajero, @PathParam("idItinerario") Long idItinerario, @PathParam("idVisitaCiudad") Long idVisitaCiudad, EventoViajeroDTO evento)
    {
        EventoViajeroEntity entity = EventoViajeroConverter.fullDTO2Entity(evento);
        EventoViajeroEntity newEntity;
        try
        {
            newEntity = eventoViajeroLogic.createEventoViajero(idViajero, idItinerario, idVisitaCiudad, entity);
        }

        catch (BusinessLogicException ex)
        {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }

        return EventoViajeroConverter.fullEntity2DTO(newEntity);
    }

    /**
     * Actualiza los datos de un evento en la lista del viajero
     * @param idViajero
     * @param id identificador del evento a modificar
     * @param idItinerario
     * @param evento evento a modificar
     * @param idVisitaCiudad
     * @return datos del evento modificado
     * @throws ItinerarioLogicException cuando no existe una evento con el id suministrado
     */
    @PUT
    @Path("{idViajero: \\d+}/{idItinerario: \\d+}/{idVisitaCiudad: \\d+}/{id: \\d+}")
    public EventoViajeroDTO updateEventoViajero(@PathParam("idViajero") Long idViajero, @PathParam("idItinerario") Long idItinerario, @PathParam("idVisitaCiudad") Long idVisitaCiudad, @PathParam("id") Long id, EventoViajeroDTO evento) throws ItinerarioLogicException {
        logger.log(Level.INFO, "Se ejecuta método updateEventoViajero con id={0}", id);

        EventoViajeroEntity entity = EventoViajeroConverter.fullDTO2Entity(evento);
        entity.setId(id);

        try
        {
            EventoViajeroEntity oldEntity = eventoViajeroLogic.getEventoViajero(idViajero, idItinerario, idVisitaCiudad, id);
            entity.setEvento(oldEntity.getEvento());
            return EventoViajeroConverter.fullEntity2DTO(eventoViajeroLogic.updateEventoViajero(idViajero, idItinerario, idVisitaCiudad, entity));
        }

        catch (BusinessLogicException ex)
        {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }

    /**
     * Elimina los datos de un evento
     * @param idViajero
     * @param id identificador del evento a eliminar
     * @param idItinerario
     * @param idVisitaCiudad
     * @throws ItinerarioLogicException cuando no existe un evento con el id suministrado
     */
    @DELETE
    @Path("{idViajero: \\d+}/{idItinerario: \\d+}/{idVisitaCiudad: \\d+}/{id: \\d+}")
    public void deleteEventoViajero(@PathParam("idViajero") Long idViajero, @PathParam("idItinerario") Long idItinerario, @PathParam("idVisitaCiudad") Long idVisitaCiudad, @PathParam("id") Long id) throws ItinerarioLogicException
    {
        try
        {
            eventoViajeroLogic.deleteEventoViajero(idViajero, idItinerario, idVisitaCiudad, id);
        }

        catch (BusinessLogicException ex)
        {
            Logger.getLogger(EventoViajeroResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
