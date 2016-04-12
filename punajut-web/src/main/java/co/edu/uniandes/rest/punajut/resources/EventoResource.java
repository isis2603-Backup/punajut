/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;
import co.edu.uniandes.punajut.api.IEventoLogic;
import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.rest.punajut.dtos.EventoDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException;
import co.edu.uniandes.rest.punajut.mocks.EventoLogicMock;
import co.edu.uniandes.rest.punajut.converters.EventoConverter;
import java.util.List;
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
 * @author r.cardenas11
 */
@Path("eventos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class EventoResource {
    	@Inject
	IEventoLogic eventoLogic;

	/**
	 * Obtiene el listado de los eventos a los  en una ciudad.
	 * @return lista de eventos
	 * @throws ItinerarioLogicException excepción retornada por la lógica
	 */
    @GET
    public List<EventoDTO> getEventoCiudad() throws UsuarioLogicException {
     
     return EventoConverter.listEntity2DTO( eventoLogic.getEventos()) ;

    }

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
