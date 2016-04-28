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
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException;
//import co.edu.uniandes.rest.punajut.mocks.EventoLogicMock;
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
import javax.ws.rs.PUT;
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

    private static final Logger logger = Logger.getLogger(EventoResource.class.getName());

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
            logger.log(Level.SEVERE, "El evento no existe", ex);
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

//	/**
//	 * Obtiene el listado de los eventos a los  en una ciudad.
//	 * @return lista de eventos
//	 * @throws ItinerarioLogicException excepción retornada por la lógica
//	 */
//    @GET
//    public List<EventoDTO> getEventoCiudad() throws UsuarioLogicException {
//
//     return EventoConverter.listEntity2DTO( eventoLogic.getEventos()) ;
//
//    }
//    /**
//     * Obtiene uno de los eventos de la ciudad
//     * @param id identificador del evento
//     * @return evento encontrado
//     * @throws ItinerarioLogicException cuando el evento no existe
//     */
//    @GET
//    @Path("{id: \\d+}")
//    public EventoDTO getCity(@PathParam("id") Long id) throws ItinerarioLogicException {
//        return eventoCiudadLogic.getCity(id);
//    }
//
//    /**
//     * Registra un evento a la ciudad
//     * @param evento evento a agregar
//     * @return datos del evento a agregar
//     * @throws ItinerarioLogicException cuando ya existe una ciudad con el id suministrado
//     */
//    @POST
//    public EventoDTO registrarEventoCiudad(EventoDTO evento) throws ItinerarioLogicException {
//        return eventoCiudadLogic.createCity(evento);
//    }
//
//    /**
//     * Modifica un evento de la lista de la ciudad.
//     * @param id identificador del evento a modificar
//     * @param evento evento a modificar
//     * @return datos del evento modificado
//     * @throws ItinerarioLogicException cuando no existe un evento con el id suministrado
//     */
//    @PUT
//    @Path("{id: \\d+}")
//    public EventoDTO modificarEventoCiudad(@PathParam("id") Long id, EventoDTO evento) throws ItinerarioLogicException {
//        return eventoCiudadLogic.updateCity(id, evento);
//    return null;
//    }
//
//    /**
//     * Elimina los datos de un evento de la lista de la ciudad.
//     * @param id identificador del evento a eliminar
//     * @throws ItinerarioLogicException cuando no existe una ciudad con el id suministrado
//     */
//    @DELETE
//    @Path("{id: \\d+}")
//    public void borrarEventoCiudad(@PathParam("id") Long id) throws ItinerarioLogicException {
//    	eventoCiudadLogic.deleteCity(id);
//    }
    }
}
