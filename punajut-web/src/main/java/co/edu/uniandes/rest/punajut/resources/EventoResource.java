/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;

import co.edu.uniandes.punajut.api.IEventoLogic;
import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.rest.punajut.dtos.EventoDTO;
import co.edu.uniandes.rest.punajut.converters.EventoConverter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author r.cardenas11
 */
@Path("eventos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@RequestScoped
public class EventoResource {

    private static final Logger LOGGER = Logger.getLogger(EventoResource.class.getName());

    @Inject
    IEventoLogic eventoLogic;

    /**
     * Obtiene la lista de los registros
     *
     * @return Coleccion de objetos de EventoDTO (representacion full)
     * @generated
     */
    @GET
    public List<EventoDTO> getEventos() {
        return EventoConverter.listEntity2DTO(eventoLogic.getEventos());
    }

    /**
     * Obtiene un evento dado su ID
     *
     * @param id identificador del objeto a consultar
     * @return Instancia de EventoDTO
     */
    @GET
    @Path("{id: \\d+}")
    public EventoDTO getEvento(@PathParam("id") Long id) {
        try {
            return EventoConverter.fullEntity2DTO(eventoLogic.getEvento(id));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, "El evento no existe", ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Crea un evento en la base de datos
     *
     * @param dto Objeto de EventoDTO con los datos nuevos (representacion full)
     * @return Objeto de EventoDTO con los datos nuevos y su ID (representacion
     * full)
     * @generated
     */
    @POST
    public EventoDTO createEvento(EventoDTO dto) {
        EventoEntity entity = EventoConverter.fullDTO2Entity(dto);
        return EventoConverter.fullEntity2DTO(eventoLogic.createEvento(entity));
    }

    /**
     * Elimina el objeto de un Evento de la base de datos
     *
     * @param id Identificador del objeto a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEvento(@PathParam("id") Long id) {
        eventoLogic.deleteEvento(id);
    }

}
